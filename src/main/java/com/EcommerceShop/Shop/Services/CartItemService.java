package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CartItemResponse;
import com.EcommerceShop.Shop.DTO.response.CartResponse;

public interface CartItemService {
    CartItemResponse create(CartItemCreateRequest request) ;
}
