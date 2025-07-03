package com.EcommerceShop.Shop.product.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateProductDetailRequest {
    private Long id ;

    private String info ;

    private Double price ;

    private Integer quantity ;
}
