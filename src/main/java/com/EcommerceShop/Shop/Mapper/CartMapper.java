package com.EcommerceShop.Shop.Mapper;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.Entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toCart(CartItemCreateRequest request) ;
}
