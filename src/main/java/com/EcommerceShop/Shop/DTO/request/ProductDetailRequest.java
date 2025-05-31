package com.EcommerceShop.Shop.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailRequest {
    private String info;
    private Float price ;
    private int quantity ;
}
