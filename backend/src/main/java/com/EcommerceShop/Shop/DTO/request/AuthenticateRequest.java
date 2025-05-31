package com.EcommerceShop.Shop.DTO.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticateRequest {
    private String username ;
    private String password ;
}
