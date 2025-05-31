package com.EcommerceShop.Shop.Repository;

import com.EcommerceShop.Shop.Entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {
}
