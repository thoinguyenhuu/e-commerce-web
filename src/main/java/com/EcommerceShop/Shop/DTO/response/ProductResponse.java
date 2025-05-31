package com.EcommerceShop.Shop.DTO.response;

import com.EcommerceShop.Shop.DTO.request.ProductDetailRequest;
import com.EcommerceShop.Shop.Entity.Product;
import com.EcommerceShop.Shop.Entity.ProductDetail;
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
    private String name;
    private String description;
    private String imageUrl;
    private Float averageRate;
    private List<ProductDetailResponse> productDetail ;
}
