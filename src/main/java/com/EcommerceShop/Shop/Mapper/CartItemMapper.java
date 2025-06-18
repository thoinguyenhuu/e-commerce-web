package com.EcommerceShop.Shop.Mapper;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CartItemResponse;
import com.EcommerceShop.Shop.DTO.response.ProductResponse;
import com.EcommerceShop.Shop.Entity.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class CartItemMapper {
    @Autowired
    protected ProductMapper productMapper ;
    public abstract CartItem toCartItem(CartItemCreateRequest request) ;
    public CartItemResponse toCartItemResponse(CartItem item){
        return CartItemResponse.builder()
                .num(item.getNum())
                .item(productMapper.toProductResponse(item.getProduct())).build();
    }
}
