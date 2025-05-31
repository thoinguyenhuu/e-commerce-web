package com.EcommerceShop.Shop.Controllers;

import com.EcommerceShop.Shop.DTO.request.ProductCreateRequest;
import com.EcommerceShop.Shop.DTO.response.ApiResponse;
import com.EcommerceShop.Shop.DTO.response.ProductResponse;
import com.EcommerceShop.Shop.Services.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class ProductController {
    ProductService productService ;

    @PostMapping
    ApiResponse<ProductResponse> create(@RequestBody  ProductCreateRequest request){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.create(request)).build();
    }

    @GetMapping("/all")
    ApiResponse<List<ProductResponse>> getAllProduct(){
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts()).build();
    }
}
