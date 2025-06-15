package com.EcommerceShop.Shop.DTO.request.Product;

import lombok.*;

@Getter
@Builder
public class ProductDetailRequest {
    private String info;
    private Float price ;
    private int quantity ;
}
