package com.EcommerceShop.Shop.order.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemRequest {
    private String detailId ;
    private int num ;
}
