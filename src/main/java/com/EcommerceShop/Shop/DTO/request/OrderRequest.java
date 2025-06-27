package com.EcommerceShop.Shop.DTO.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemRequest {
    private String detailId ;
    private String productId ;
    private int num ;
}
