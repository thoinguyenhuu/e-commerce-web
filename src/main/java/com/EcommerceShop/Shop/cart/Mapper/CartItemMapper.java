package com.EcommerceShop.Shop.cart.Mapper;

import com.EcommerceShop.Shop.cart.dto.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.cart.dto.response.CartItemResponse;
import com.EcommerceShop.Shop.cart.Entity.CartItem;
import com.EcommerceShop.Shop.product.ProductMapper;
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
                .detail(productMapper.toProductDetailResponse(item.getItem()))
                .item(productMapper.toProductResponse(item.getItem().getProduct())).build();
    }
}
