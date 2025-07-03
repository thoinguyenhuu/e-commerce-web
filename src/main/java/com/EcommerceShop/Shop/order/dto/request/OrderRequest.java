package com.EcommerceShop.Shop.order.dto.request;

import com.EcommerceShop.Shop.address.dto.request.AddressRequest;
import com.EcommerceShop.Shop.order.Entity.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderRequest {
    private List<OrderItemRequest> items ;
    private AddressRequest shippingAddress;
    private PaymentMethod paymentMethod;
    private String discountCode; // Optional
    private String note; // Optional
    // Getters, setters
}
