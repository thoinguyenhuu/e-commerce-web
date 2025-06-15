package com.EcommerceShop.Shop.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {
    private String id ;
    private String info;
    private Float price ;
    private int quantity ;
}
