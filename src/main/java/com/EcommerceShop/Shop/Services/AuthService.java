package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.LoginRequest;
import com.EcommerceShop.Shop.DTO.request.LogoutRequest;
import com.EcommerceShop.Shop.DTO.request.RefreshAccessTokenRequest;
import com.EcommerceShop.Shop.DTO.response.AuthenticateResponse;


import java.text.ParseException;


public interface AuthService {
    public AuthenticateResponse login(LoginRequest request) ;

    public AuthenticateResponse refresh(RefreshAccessTokenRequest request) throws ParseException;

    public boolean authenticate(String token) ;

    public void logout(LogoutRequest request) ;
}
