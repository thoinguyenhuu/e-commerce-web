package com.EcommerceShop.Shop.cart.Repository;

import com.EcommerceShop.Shop.cart.Entity.Cart;
import com.EcommerceShop.Shop.cart.Entity.CartItem;
import com.EcommerceShop.Shop.product.Entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findByCart(Cart cart) ;
    CartItem findByItem(ProductDetail item) ;
}
