package com.EcommerceShop.Shop.DTO.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemResponse {
    private int num ;
    private ProductDetailResponse detail ;
    private ProductResponse item ;
}
