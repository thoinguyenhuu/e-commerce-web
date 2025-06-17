package com.EcommerceShop.Shop.Mapper;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CartItemResponse;
import com.EcommerceShop.Shop.Entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CartItemMapper {
     public abstract CartItem toCartItem(CartItemCreateRequest request) ;
     public CartItemResponse toCartItemResponse(CartItem item){
        return CartItemResponse.builder()
                .num(item.getNum())
                .product_id(item.getProduct().getId()).build();
     }
}
