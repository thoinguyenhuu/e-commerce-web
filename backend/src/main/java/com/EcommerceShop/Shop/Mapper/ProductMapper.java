package com.EcommerceShop.Shop.Mapper;

import com.EcommerceShop.Shop.DTO.request.ProductCreateRequest;
import com.EcommerceShop.Shop.DTO.response.ProductDetailResponse;
import com.EcommerceShop.Shop.DTO.response.ProductResponse;
import com.EcommerceShop.Shop.Entity.Product;
import com.EcommerceShop.Shop.Entity.ProductDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductMapper {
    public Product toProduct(ProductCreateRequest request){
        return  Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .productCategories(new ArrayList<>())
                .productDetails(new ArrayList<>())
                .build();
    } ;

    public ProductResponse toProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .averageRate(product.getAverageRate())
                .productDetail(product.getProductDetails().stream().map(this::toProductDetailResponse).toList()).build();
    }
    public ProductDetailResponse toProductDetailResponse(ProductDetail productDetail){
        return ProductDetailResponse.builder()
                .info(productDetail.getInfo())
                .price(productDetail.getPrice())
                .quantity(productDetail.getQuantity())
                .build();
    }
}
