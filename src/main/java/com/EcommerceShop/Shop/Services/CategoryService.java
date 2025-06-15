package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.CategoryCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CategoryResponse;
import com.EcommerceShop.Shop.Entity.Category;


import java.util.List;

public interface CategoryService {
    public CategoryResponse create(CategoryCreateRequest request) ;

    public List<CategoryResponse> getALl() ;

    public  Category getByName(String name) ;
}
