package com.EcommerceShop.Shop.DTO.request;

import lombok.*;

@Getter
@Builder
public class LoginRequest {
    private String username ;
    private String password ;
}
