package com.EcommerceShop.Shop.auth.dto.request;

import lombok.*;

@Getter
@Builder
public class LoginRequest {
    private String username ;
    private String password ;
    private String role ;
}
