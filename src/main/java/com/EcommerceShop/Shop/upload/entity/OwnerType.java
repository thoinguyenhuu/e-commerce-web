package com.EcommerceShop.Shop.upload.entity;

import com.EcommerceShop.Shop.category.CategoryRepository;
import com.EcommerceShop.Shop.exception.AppException;
import com.EcommerceShop.Shop.exception.ErrorCode;
import com.EcommerceShop.Shop.product.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public enum OwnerType {
//    USER,
    PRODUCT,
    CATEGORY,
    BRAND

    ;
    public boolean validate(Long ownerId, JpaRepository<?,Long> jpaRepository){
        return  jpaRepository.findById(ownerId).isPresent();
    }
}
