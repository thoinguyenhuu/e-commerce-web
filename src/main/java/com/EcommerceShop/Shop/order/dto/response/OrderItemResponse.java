package com.EcommerceShop.Shop.order.dto.response;

import com.EcommerceShop.Shop.product.dto.response.ProductDetailResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class OrderItemResponse {
    private String id ;

    private ProductDetailResponse item ;

    private Long num ;

}
