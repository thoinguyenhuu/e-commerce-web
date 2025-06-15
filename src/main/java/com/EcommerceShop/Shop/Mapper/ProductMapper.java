package com.EcommerceShop.Shop.Mapper;

import com.EcommerceShop.Shop.DTO.request.Product.ProductRequest;
import com.EcommerceShop.Shop.DTO.response.ProductDetailResponse;
import com.EcommerceShop.Shop.DTO.response.ProductResponse;
import com.EcommerceShop.Shop.Entity.Product;
import com.EcommerceShop.Shop.Entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public abstract Product toProduct(ProductRequest request) ;

    public ProductResponse toProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .averageRate(product.getAverageRate())
                .productDetail(product.getProductDetails().stream().map(this::toProductDetailResponse).toList()).build();
    }
    public abstract ProductDetailResponse toProductDetailResponse(ProductDetail productDetail) ;

    public abstract void update(@MappingTarget Product product, ProductRequest productRequest) ;

}
