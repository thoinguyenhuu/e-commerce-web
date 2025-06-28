package com.EcommerceShop.Shop.product.repository;

import com.EcommerceShop.Shop.product.Entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {
}
