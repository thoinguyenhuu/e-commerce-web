package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.Product.ProductDetailRequest;
import com.EcommerceShop.Shop.DTO.request.Product.ProductRequest;
import com.EcommerceShop.Shop.DTO.request.Product.UpdateProductDetailRequest;
import com.EcommerceShop.Shop.DTO.response.ProductResponse;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProductService {
    public ProductResponse create(ProductRequest request) ;

    public List<ProductResponse> getAllProducts() ;

//    public List<ProductResponse> getByCategory(String name) ;
    ProductResponse addADetailToProduct(String productId, ProductDetailRequest request) ;

    public ProductResponse updateProductInfo(String id, ProductRequest request) ;

    public ProductResponse updateProductDetail(String id, UpdateProductDetailRequest request) ;

    public List<ProductResponse> getProductPaging(Pageable pageable) ;

    void deleteProduct(String productId) ;
}
