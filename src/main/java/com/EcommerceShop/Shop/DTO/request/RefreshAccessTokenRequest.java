package com.EcommerceShop.Shop.DTO.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RefreshAccessTokenRequest {
    private String token ;
}
