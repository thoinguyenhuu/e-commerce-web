package com.EcommerceShop.Shop.cart.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartItemUpdateRequest {
    private Long detailId ;
    private Integer num ;
}
