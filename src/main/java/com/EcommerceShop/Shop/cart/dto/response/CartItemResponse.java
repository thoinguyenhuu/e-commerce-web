package com.EcommerceShop.Shop.cart.dto.response;

import com.EcommerceShop.Shop.product.dto.response.ProductDetailResponse;
import com.EcommerceShop.Shop.product.dto.response.ProductResponse;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemResponse {
    private int num ;
    private ProductDetailResponse detail ;
    private ProductResponse item ;
}
