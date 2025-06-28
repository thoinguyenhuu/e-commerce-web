package com.EcommerceShop.Shop.product.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateProductDetailRequest {
    private String id ;

    private String info ;

    private Double price ;

    private Integer quantity ;
}
