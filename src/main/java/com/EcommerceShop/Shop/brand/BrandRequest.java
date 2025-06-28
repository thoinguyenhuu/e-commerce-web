package com.EcommerceShop.Shop.brand;

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
