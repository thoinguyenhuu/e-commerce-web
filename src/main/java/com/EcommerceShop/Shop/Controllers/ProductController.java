package com.EcommerceShop.Shop.Controllers;

import com.EcommerceShop.Shop.DTO.request.Product.ProductDetailRequest;
import com.EcommerceShop.Shop.DTO.request.Product.ProductRequest;
import com.EcommerceShop.Shop.DTO.request.Product.UpdateProductDetailRequest;
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

    @PostMapping("/{productId}/detail")
    ApiResponseWrapper<ProductResponse> addADetailToProduct(@PathVariable String productId, @RequestBody ProductDetailRequest request){
        return ApiResponseWrapper.<ProductResponse>builder().build();
    }

    @PutMapping("/{productId}/info")
    ApiResponseWrapper<ProductResponse> updateProduct(@PathVariable String productId, @RequestBody ProductRequest request){
        return ApiResponseWrapper.<ProductResponse>builder()
                .result(productService.updateProductInfo(productId,request)).build() ;
    }

    @PutMapping("/{productId}/detail")
    ApiResponseWrapper<ProductResponse> updateProductDetail(@PathVariable String productId, @RequestBody UpdateProductDetailRequest request){
        return ApiResponseWrapper.<ProductResponse>builder()
                .result(productService.updateProductDetail(productId,request)).build() ;
    }

}
