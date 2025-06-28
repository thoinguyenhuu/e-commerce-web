package com.EcommerceShop.Shop.product.dto.request;

import lombok.*;

@Getter
@Builder
public class ProductDetailRequest {
    private String info;
    private Float price ;
    private int quantity ;
}
