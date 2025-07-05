package com.EcommerceShop.Shop.product.Controller;

import com.EcommerceShop.Shop.product.ProductService;
import com.EcommerceShop.Shop.product.dto.request.ProductDetailRequest;
import com.EcommerceShop.Shop.product.dto.request.ProductRequest;
import com.EcommerceShop.Shop.product.dto.request.UpdateProductDetailRequest;
import com.EcommerceShop.Shop.util.ApiResponseWrapper;
import com.EcommerceShop.Shop.product.dto.response.ProductResponse;
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

    @GetMapping
    ApiResponseWrapper<List<ProductResponse>> getProductsPaging(Pageable pageable){
        return ApiResponseWrapper.<List<ProductResponse>>builder()
                .data(productService.getProductPaging(pageable)).build();
    }

    @GetMapping("/{productId}")
    ApiResponseWrapper<ProductResponse> getProductById(@PathVariable Long productId){
        return ApiResponseWrapper.<ProductResponse>builder()
                .data(productService.getProductById(productId)).build();
    }

    @PostMapping
    ApiResponseWrapper<ProductResponse> create(@RequestBody ProductRequest request){
        return ApiResponseWrapper.<ProductResponse>builder()
                .data(productService.create(request)).build();
    }

    @PostMapping("/{productId}/detail")
    ApiResponseWrapper<ProductResponse> addADetailToProduct(@PathVariable Long productId, @RequestBody ProductDetailRequest request){
        return ApiResponseWrapper.<ProductResponse>builder()
                .data(productService.addADetailToProduct(productId,request)).build();
    }

    @PutMapping("/{productId}/info")
    ApiResponseWrapper<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest request){
        return ApiResponseWrapper.<ProductResponse>builder()
                .data(productService.updateProductInfo(productId,request)).build() ;
    }

    @PutMapping("/{productId}/detail")
    ApiResponseWrapper<ProductResponse> updateProductDetail(@PathVariable Long productId, @RequestBody UpdateProductDetailRequest request){
        return ApiResponseWrapper.<ProductResponse>builder()
                .data(productService.updateProductDetail(productId,request)).build() ;
    }

    @DeleteMapping("/{productId}")
    ApiResponseWrapper<?> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ApiResponseWrapper.builder()
                .code(200)
                .message(String.format("Product %s have been deleted", productId))
                .build();
    }

    @DeleteMapping("/{productId}/detail/{detailId}")
    ApiResponseWrapper<?> deleteDetail(@PathVariable Long productId, @PathVariable Long detailId){
        productService.deleteDetail(productId, detailId);
        return ApiResponseWrapper.builder()
                .code(200)
                .message(String.format("Product detail %s has been deleted!", detailId)).build();
    }



}
