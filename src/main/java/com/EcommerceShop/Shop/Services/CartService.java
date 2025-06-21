package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.response.CartResponse;
import com.EcommerceShop.Shop.Entity.Cart;

public interface CartService {
    Cart create();

    CartResponse getCartByUser(String username);
}