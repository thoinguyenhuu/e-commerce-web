package com.EcommerceShop.Shop.Repository;

import com.EcommerceShop.Shop.DTO.response.ProductResponse;
import com.EcommerceShop.Shop.Entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
