package com.EcommerceShop.Shop.DTO.request.Product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateProductDetailRequest {
    private String id ;

    private String info ;

    private float price ;

    private int quantity ;
}
