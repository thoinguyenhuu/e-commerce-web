package com.EcommerceShop.Shop.Controllers;


import com.EcommerceShop.Shop.DTO.response.ApiResponseWrapper;
import com.EcommerceShop.Shop.DTO.response.CartResponse;
import com.EcommerceShop.Shop.Services.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CartController {
    CartService cartService ;

    @GetMapping("/me")
    ApiResponseWrapper<CartResponse> getCartOfUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        return ApiResponseWrapper.<CartResponse>builder()
                .result(cartService.getCartByUser(username)).build();
    }

}
