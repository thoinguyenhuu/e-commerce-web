package com.EcommerceShop.Shop.Controllers;

import com.EcommerceShop.Shop.DTO.request.CreateShopRequest;
import com.EcommerceShop.Shop.DTO.response.ApiResponse;
import com.EcommerceShop.Shop.DTO.response.ShopResponse;
import com.EcommerceShop.Shop.Services.ShopService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class SellerController {
    ShopService shopService ;
    @PostMapping("/register")
    public ApiResponse<ShopResponse> createShop(@RequestBody CreateShopRequest request){
        return ApiResponse.<ShopResponse>builder()
                .result(shopService.registerShop(request)).build() ;
    }
}
