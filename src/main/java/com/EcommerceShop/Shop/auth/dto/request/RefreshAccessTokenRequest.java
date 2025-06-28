package com.EcommerceShop.Shop.auth.dto.request;

import lombok.*;

@Getter
@Builder
public class RefreshAccessTokenRequest {
    private String token ;
}
