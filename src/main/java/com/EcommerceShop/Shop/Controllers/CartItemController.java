package com.EcommerceShop.Shop.Controllers;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.DTO.response.ApiResponse;
import com.EcommerceShop.Shop.DTO.response.CartItemResponse;
import com.EcommerceShop.Shop.DTO.response.CartResponse;
import com.EcommerceShop.Shop.Services.CartItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-item")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemController {
    CartItemService cartItemService;

    @PostMapping
    ApiResponse<CartItemResponse> createCartItem(@RequestBody CartItemCreateRequest request){
        return ApiResponse.<CartItemResponse>builder()
                .result(cartItemService.create(request)).build();
    }
}
