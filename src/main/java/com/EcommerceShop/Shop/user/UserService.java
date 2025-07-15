package com.EcommerceShop.Shop.user;

import com.EcommerceShop.Shop.exception.AppException;
import com.EcommerceShop.Shop.exception.ErrorCode;
import com.EcommerceShop.Shop.user.Entity.Role;
import com.EcommerceShop.Shop.user.Entity.User;
import com.EcommerceShop.Shop.user.dto.request.UpdatePasswordRequest;
import com.EcommerceShop.Shop.user.dto.request.UserCreationRequest;
import com.EcommerceShop.Shop.user.dto.request.UserUpdateRequest;
import com.EcommerceShop.Shop.user.dto.response.UserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;


import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService  {

    UserRepository userRepository ;
    UserMapper userMapper ;
    PasswordEncoder passwordEncoder ;

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new AppException(ErrorCode.USER_EXISTED) ;
        }
        User user = userMapper.toUser(request) ;
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user) ;
        return userMapper.toUserResponse(user) ;
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.claims['id']")
    public UserResponse updateUser(String userId, UserUpdateRequest request){

        User user = userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        userMapper.updateUser(user,request);
        userRepository.save(user) ;
        return userMapper.toUserResponse(user) ;
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList() ;
    }

    public UserResponse getMyInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        return userMapper.toUserResponse(userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse getUser(String userId){
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED))) ;
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.claims['id']")
    public void deleteUser(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        userRepository.delete(user);
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.claims['id']")
    public void updatePassword(String userId , UpdatePasswordRequest request){
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED)) ;
        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            throw new AppException(ErrorCode.PASSWORD_WRONG) ;
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user) ;
    }
}

