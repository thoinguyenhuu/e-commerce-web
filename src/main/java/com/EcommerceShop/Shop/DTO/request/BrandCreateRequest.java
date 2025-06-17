package com.EcommerceShop.Shop.DTO.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandCreateRequest {
    String name ;

    String description ;

    String logoUrl ;
}
