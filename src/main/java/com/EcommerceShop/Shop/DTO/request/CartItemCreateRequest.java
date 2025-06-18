package com.EcommerceShop.Shop.DTO.request;

import jakarta.persistence.GeneratedValue;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CartItemCreateRequest {
    private String product_id ;
    private int num ;
}
