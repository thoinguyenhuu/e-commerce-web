package com.EcommerceShop.Shop.Mapper;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CartItemResponse;
import com.EcommerceShop.Shop.DTO.response.CartResponse;
import com.EcommerceShop.Shop.Entity.Cart;
import com.EcommerceShop.Shop.Entity.CartItem;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CartMapper {
    @Autowired
    CartItemMapper cartItemMapper ;
    public abstract Cart toCart(CartItemCreateRequest request) ;
    public CartResponse toCartResponse(Cart cart){
        return CartResponse.builder()
                .id(cart.getId())
                .user_id(cart.getUser().getId())
                .cartItems(cart.getCartItems()
                        .stream()
                        .map(cartItemMapper::toCartItemResponse)
                        .toList()).build();
    }
}
