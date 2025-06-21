package com.EcommerceShop.Shop.Controllers;

import com.EcommerceShop.Shop.DTO.request.CategoryCreateRequest;
import com.EcommerceShop.Shop.DTO.response.ApiResponseWrapper;
import com.EcommerceShop.Shop.DTO.response.CategoryResponse;
import com.EcommerceShop.Shop.Services.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService ;

    @PostMapping
    ApiResponseWrapper<CategoryResponse> create(@RequestBody CategoryCreateRequest request){
        return ApiResponseWrapper.<CategoryResponse>builder()
                .result(categoryService.create(request)).build();
    }

    @GetMapping("/all")
    ApiResponseWrapper<List<CategoryResponse>> getAll(){
        return ApiResponseWrapper.<List<CategoryResponse>>builder()
                .result(categoryService.getALl()).build() ;
    }
}
