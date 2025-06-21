package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CartItemResponse;
import com.EcommerceShop.Shop.DTO.response.CartResponse;

import java.util.List;

public interface CartItemService {
    CartItemResponse create(CartItemCreateRequest request) ;
    void deleteCartItem(String itemId) ;
    CartItemResponse update(String id) ;
    List<CartItemResponse> getList() ;
}
