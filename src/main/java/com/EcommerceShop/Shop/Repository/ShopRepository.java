package com.EcommerceShop.Shop.Repository;

import com.EcommerceShop.Shop.Entity.Shop;
import com.EcommerceShop.Shop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,String> {
    Shop findByUser(User user) ;
}
