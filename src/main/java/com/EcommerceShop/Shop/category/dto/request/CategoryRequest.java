package com.EcommerceShop.Shop.category.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
public class CategoryRequest {
    private String name ;
    private String description  ;
    private String urlImage ;
}
