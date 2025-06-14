package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.Product.ProductRequest;
import com.EcommerceShop.Shop.DTO.request.Product.UpdateProductDetailRequest;
import com.EcommerceShop.Shop.DTO.response.ProductResponse;


import java.util.List;

public interface ProductService {
    public ProductResponse create(ProductRequest request) ;

    public List<ProductResponse> getAllProducts() ;

    public List<ProductResponse> getByCategory(String name) ;

    public ProductResponse updateProductInfo(String id, ProductRequest request) ;


    public ProductResponse updateProductDetail(String id, UpdateProductDetailRequest request) ;
}
