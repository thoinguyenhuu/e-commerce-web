package com.EcommerceShop.Shop.auth;

import com.EcommerceShop.Shop.auth.dto.request.LoginRequest;
import com.EcommerceShop.Shop.auth.dto.request.LogoutRequest;
import com.EcommerceShop.Shop.auth.dto.request.RefreshAccessTokenRequest;
import com.EcommerceShop.Shop.user.dto.response.UserResponse;
import com.EcommerceShop.Shop.util.ApiResponseWrapper;
import com.EcommerceShop.Shop.auth.dto.response.AuthenticateResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;
    @PostMapping("/login")
    public ApiResponseWrapper<AuthenticateResponse> login(@RequestBody LoginRequest request){
        return ApiResponseWrapper.<AuthenticateResponse>builder()
                .result(authService.login(request)).build();
    }

    @PostMapping("/refresh-token")
    public ApiResponseWrapper<AuthenticateResponse> refresh(@RequestBody RefreshAccessTokenRequest request) throws ParseException {
        return ApiResponseWrapper.<AuthenticateResponse>builder()
                .result(authService.refresh(request)).build();
    }

    @PostMapping("/logout")
    public ApiResponseWrapper<Void> logout(@RequestBody LogoutRequest request){
        authService.logout(request);
        return ApiResponseWrapper.<Void>builder().build();
    }
}
