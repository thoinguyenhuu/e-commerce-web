package com.EcommerceShop.Shop.Controllers;

import com.EcommerceShop.Shop.DTO.request.LoginRequest;
import com.EcommerceShop.Shop.DTO.request.LogoutRequest;
import com.EcommerceShop.Shop.DTO.request.RefreshAccessTokenRequest;
import com.EcommerceShop.Shop.DTO.response.ApiResponseWrapper;
import com.EcommerceShop.Shop.DTO.response.AuthenticateResponse;
import com.EcommerceShop.Shop.Services.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
