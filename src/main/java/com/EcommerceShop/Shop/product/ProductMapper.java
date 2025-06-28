package com.EcommerceShop.Shop.product;

import com.EcommerceShop.Shop.product.Entity.Product;
import com.EcommerceShop.Shop.product.Entity.ProductDetail;
import com.EcommerceShop.Shop.product.dto.request.ProductRequest;
import com.EcommerceShop.Shop.product.dto.response.ProductDetailResponse;
import com.EcommerceShop.Shop.product.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .productDetails(new ArrayList<>())
                .productCategories(new ArrayList<>())
                .averageRate(0.0F)
                .imageUrl(request.getImageUrl())
                .description(request.getDescription())
                .orderItems(new ArrayList<>())
                .feedback(new ArrayList<>())
                .build();
    }

    public ProductResponse toProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .averageRate(product.getAverageRate())
                .productDetail(product.getProductDetails().stream().map(this::toProductDetailResponse).toList()).build();
    }
    public abstract ProductDetailResponse toProductDetailResponse(ProductDetail productDetail) ;

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "productCategories", ignore = true)
    @Mapping(target = "productDetails", ignore = true )
    public abstract void update(@MappingTarget Product product, ProductRequest productRequest) ;

}
