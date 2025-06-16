package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.CreateShopRequest;
import com.EcommerceShop.Shop.DTO.response.ShopResponse;

public interface ShopService {
    ShopResponse registerShop(CreateShopRequest request) ;


}
