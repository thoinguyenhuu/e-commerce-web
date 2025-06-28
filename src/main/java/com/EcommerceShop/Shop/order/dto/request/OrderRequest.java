package com.EcommerceShop.Shop.order.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderRequest {
    List<OrderItemRequest> items ;
    private String shippingAddress;
    private String receiverName;
    private String receiverPhone;
    private String paymentMethod;
    private String discountCode; // Optional
    private String note; // Optional
    // Getters, setters
}
