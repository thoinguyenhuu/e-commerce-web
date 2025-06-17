package com.EcommerceShop.Shop.Repository;

import com.EcommerceShop.Shop.Entity.Cart;
import com.EcommerceShop.Shop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {
    Cart findByUser(User user) ;
}
