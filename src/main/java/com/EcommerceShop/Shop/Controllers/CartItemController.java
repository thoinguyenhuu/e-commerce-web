package com.EcommerceShop.Shop.Controllers;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.DTO.response.ApiResponseWrapper;
import com.EcommerceShop.Shop.DTO.response.CartItemResponse;
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
    ApiResponseWrapper<CartItemResponse> createCartItem(@RequestBody CartItemCreateRequest request){
        return ApiResponseWrapper.<CartItemResponse>builder()
                .result(cartItemService.create(request)).build();
    }

    @PutMapping("/{itemId}")
    ApiResponseWrapper<CartItemResponse> updateCartItem(@PathVariable String itemId){
        return ApiResponseWrapper.<CartItemResponse>builder()
                .result(cartItemService.update(itemId)).build();
    }

    @DeleteMapping("/{itemId}")
    ApiResponseWrapper<?> deleteCartItem(@PathVariable String itemId){
        cartItemService.deleteCartItem(itemId);
        return ApiResponseWrapper.builder()
                .status(200)
                .message("Item has been deleted")
                .build();
    }
//    @GetMapping("all")
//    ApiResponseWrapper<List<CartItemResponse>> getListCartOfUser(){
//        return ApiResponseWrapper.<List<CartItemResponse>>builder().build();
//    }
}
