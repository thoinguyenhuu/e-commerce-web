package com.EcommerceShop.Shop.auth;

import com.EcommerceShop.Shop.auth.dto.request.LoginRequest;
import com.EcommerceShop.Shop.auth.dto.request.LogoutRequest;
import com.EcommerceShop.Shop.auth.dto.request.RefreshAccessTokenRequest;
import com.EcommerceShop.Shop.auth.dto.response.AuthenticateResponse;
import com.EcommerceShop.Shop.exception.AppException;
import com.EcommerceShop.Shop.exception.ErrorCode;
import com.EcommerceShop.Shop.user.Entity.User;
import com.EcommerceShop.Shop.user.UserRepository;
import com.EcommerceShop.Shop.user.dto.response.UserResponse;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService{
    UserRepository userRepository ;
    PasswordEncoder passwordEncoder ;
    BlacklistTokenRepository blacklistTokenRepository ;
    @NonFinal
    @Value("${jwt.access_key}")
    String access_key ;
    @NonFinal
    @Value("${jwt.refresh_key}")
    String refresh_key ;

    public AuthenticateResponse login(LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        if(!user.getRole().toString().equals(request.getRole())){
            throw new AppException(ErrorCode.UNAUTHORIZED) ;
        }
        boolean authenticate = passwordEncoder.matches(request.getPassword(), user.getPassword()) ;

        if(!authenticate) throw new AppException(ErrorCode.UNAUTHENTICATED) ;

        return AuthenticateResponse.builder()
                .accessToken(generateToken(user,access_key,60, ChronoUnit.MINUTES))
                .refreshToken(generateToken(user,refresh_key,7,ChronoUnit.DAYS)).build();
    }

    public AuthenticateResponse refresh(RefreshAccessTokenRequest request) throws ParseException {
        if(blacklistTokenRepository.findById(request.getToken()).isPresent()){
            throw new AppException(ErrorCode.INVALID_TOKEN) ;
        }
        boolean isValid = true ;
        Pair<SignedJWT, Date> response ;
        try {
            response  =  verifyToken(request.getToken(), true) ;
        } catch (ParseException | JOSEException e){
            throw new AppException(ErrorCode.UNAUTHENTICATED) ;
        }
        User user = userRepository.findByUsername(response.getLeft().getJWTClaimsSet().getSubject()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        String access_token = generateToken(user,access_key,60,ChronoUnit.MINUTES) ;
        return AuthenticateResponse.builder().accessToken(access_token).build() ;
    }


    public boolean authenticate(String token) {
        boolean valid = true ;
        try{
            verifyToken(token, false) ;
        }
        catch (Exception e){
            valid = false;
        }
        return valid ;
    }

    public void logout(LogoutRequest request) {
        Pair<SignedJWT,Date> x ;
        try{
            x = verifyToken(request.getToken(), true) ;
        }
        catch (Exception e){
            throw new AppException(ErrorCode.INVALID_TOKEN) ;
        }
        blacklistTokenRepository.save(BlacklistToken.builder()
                .token(request.getToken())
                .expired_date(x.getRight()).build()) ;
    }

    private Pair<SignedJWT,Date> verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier = (!isRefresh) ? new MACVerifier(access_key.getBytes()) : new MACVerifier(refresh_key.getBytes()) ;

        SignedJWT signedJWT = SignedJWT.parse(token) ;

        var verify = signedJWT.verify(jwsVerifier) ;
        Date date = signedJWT.getJWTClaimsSet().getExpirationTime() ;
        if(!(verify && date.after(new Date()))){
            throw new AppException(ErrorCode.UNAUTHENTICATED) ;
        }
        return Pair.of(signedJWT,date);
    }

    private String generateToken(User user, String key , int time, ChronoUnit chronoUnit){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512) ;
        JWTClaimsSet jwtClaimsSet1 = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .claim("role", user.getRole().toString())
                .claim("id", user.getId())
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(time, chronoUnit).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .build() ;
        Payload payload = new Payload(jwtClaimsSet1.toJSONObject()) ;

        JWSObject jwsObject = new JWSObject(jwsHeader, payload) ;
        try{
            jwsObject.sign(new MACSigner(key.getBytes())) ;
        }
        catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize() ;
    }


}
