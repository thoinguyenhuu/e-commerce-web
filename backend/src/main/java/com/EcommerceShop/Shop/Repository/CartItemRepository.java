package com.EcommerceShop.Shop.Repository;

import com.EcommerceShop.Shop.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<Cart,String> {
}
