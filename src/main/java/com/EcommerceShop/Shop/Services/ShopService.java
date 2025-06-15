package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.ShopCreateRequest;
import com.EcommerceShop.Shop.DTO.response.ShopResponse;

public interface ShopService {
    ShopResponse create(ShopCreateRequest request) ;


}
