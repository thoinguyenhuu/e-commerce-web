package com.EcommerceShop.Shop.cart.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartItemCreateRequest {
    private String productId ;
    private String detailId ;
    private int num ;
}
