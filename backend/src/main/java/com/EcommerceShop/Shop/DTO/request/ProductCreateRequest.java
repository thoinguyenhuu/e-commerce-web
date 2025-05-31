package com.EcommerceShop.Shop.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    private String name;
    private String description;
    private String imageUrl;
    private List<ProductDetailRequest> productDetails ;
    private List<String> productCategory ;
}
