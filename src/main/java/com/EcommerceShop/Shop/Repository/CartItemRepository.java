package com.EcommerceShop.Shop.Repository;

import com.EcommerceShop.Shop.Entity.Cart;
import com.EcommerceShop.Shop.Entity.CartItem;
import com.EcommerceShop.Shop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findByCart(Cart cart) ;
}
