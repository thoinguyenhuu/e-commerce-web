package com.EcommerceShop.Shop.Mapper;

import com.EcommerceShop.Shop.DTO.request.CategoryCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CategoryResponse;
import com.EcommerceShop.Shop.Entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreateRequest request) ;
    CategoryResponse toCategoryResponse(Category category) ;
}
