package com.EcommerceShop.Shop.category;

import com.EcommerceShop.Shop.category.dto.request.CategoryRequest;
import com.EcommerceShop.Shop.category.dto.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {
    Category toCategory(CategoryRequest request) ;
    CategoryResponse toCategoryResponse(Category category) ;

    void update(@MappingTarget Category category, CategoryRequest request) ;
}
