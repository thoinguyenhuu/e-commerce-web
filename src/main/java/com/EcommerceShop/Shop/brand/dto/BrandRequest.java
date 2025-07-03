package com.EcommerceShop.Shop.brand.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandRequest {
    String name ;

    String description ;

    String logoUrl ;
}
