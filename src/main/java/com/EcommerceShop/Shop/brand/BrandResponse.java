package com.EcommerceShop.Shop.brand;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse {
    private Long id ;
    private String name ;
    private String description ;
    private String logoUrl ;
}
