package com.EcommerceShop.Shop.DTO.request.Product;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private String imageUrl;
    private List<ProductDetailRequest> productDetails ;
    private List<String> productCategory ;
}
