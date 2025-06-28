package com.EcommerceShop.Shop.cart.Repository;

import com.EcommerceShop.Shop.cart.Entity.Cart;
import com.EcommerceShop.Shop.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {
    Cart findByUser(User user) ;
}
