package com.EcommerceShop.Shop.Repository;

import com.EcommerceShop.Shop.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,String> {
    Brand findByName(String name ) ;
}
