package com.EcommerceShop.Shop.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String id ;
    private String name;
    private String description;
    private String imageUrl;
    private Float averageRate;
    private List<ProductDetailResponse> productDetail ;
}
