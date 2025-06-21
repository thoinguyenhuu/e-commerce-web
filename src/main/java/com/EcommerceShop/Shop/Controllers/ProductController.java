package com.EcommerceShop.Shop.Controllers;

import com.EcommerceShop.Shop.DTO.request.Product.ProductRequest;
import com.EcommerceShop.Shop.DTO.response.ApiResponseWrapper;
import com.EcommerceShop.Shop.DTO.response.ProductResponse;
import com.EcommerceShop.Shop.Services.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class ProductController {
    ProductService productService ;

    @PostMapping
    ApiResponseWrapper<ProductResponse> create(@RequestBody ProductRequest request){
        return ApiResponseWrapper.<ProductResponse>builder()
                .result(productService.create(request)).build();
    }

    @GetMapping("/all")
    ApiResponseWrapper<List<ProductResponse>> getAllProduct(){
        return ApiResponseWrapper.<List<ProductResponse>>builder()
                .result(productService.getAllProducts()).build();
    }

    @GetMapping
    ApiResponseWrapper<List<ProductResponse>> getByCategory(@RequestParam String category){
        return ApiResponseWrapper.<List<ProductResponse>>builder()
                .result(productService.getByCategory(category)).build() ;
    }

    @GetMapping("/paging")
    ApiResponseWrapper<List<ProductResponse>> getProductsPaging(Pageable pageable){
        return ApiResponseWrapper.<List<ProductResponse>>builder()
                .result(productService.getProductPaging(pageable)).build();
    }

    @PutMapping("/{id}/info")
    ApiResponseWrapper<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest request){
        return ApiResponseWrapper.<ProductResponse>builder()
                .result(productService.updateProductInfo(id,request)).build() ;
    }


//    @PutMapping("/detail/{id}")
//    ApiResponse<ProductResponse> updateProductDetail(@PathVariable String id, @RequestBody UpdateProductDetailRequest request){
//        return ApiResponse.<ProductResponse>builder()
//                .result(productService.).build() ;
//    }

}
