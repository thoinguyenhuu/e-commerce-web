package com.EcommerceShop.Shop.order.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderNotify {
    String message ;
    String orderId ;
}
