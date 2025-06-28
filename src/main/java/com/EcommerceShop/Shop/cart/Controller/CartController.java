package com.EcommerceShop.Shop.cart.Controller;


import com.EcommerceShop.Shop.util.ApiResponseWrapper;
import com.EcommerceShop.Shop.cart.dto.response.CartResponse;
import com.EcommerceShop.Shop.cart.Service.CartService;
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
