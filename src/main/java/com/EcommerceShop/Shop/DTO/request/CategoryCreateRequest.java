package com.EcommerceShop.Shop.DTO.request;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
public class CategoryCreateRequest {
    private String name ;
    private String description  ;

}
