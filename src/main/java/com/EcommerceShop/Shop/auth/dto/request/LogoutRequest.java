package com.EcommerceShop.Shop.auth.dto.request;

import lombok.*;

@Getter
@Builder
public class LogoutRequest {
    private String token ;
}
