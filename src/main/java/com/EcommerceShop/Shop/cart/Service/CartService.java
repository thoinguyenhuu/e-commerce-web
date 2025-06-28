package com.EcommerceShop.Shop.cart.Service;

import com.EcommerceShop.Shop.cart.Mapper.CartMapper;
import com.EcommerceShop.Shop.cart.Repository.CartRepository;
import com.EcommerceShop.Shop.cart.dto.response.CartResponse;
import com.EcommerceShop.Shop.cart.Entity.Cart;
import com.EcommerceShop.Shop.exception.AppException;
import com.EcommerceShop.Shop.exception.ErrorCode;
import com.EcommerceShop.Shop.user.Entity.User;
import com.EcommerceShop.Shop.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CartService{
    CartRepository cartRepository ;
    UserRepository userRepository ;
    CartMapper cartMapper ;

    public Cart create(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;

        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        Cart cart = Cart.builder()
                .cartItems(new ArrayList<>())
                .user(user)
                .build();
        user.setCarts(cart);
        return cartRepository.save(cart) ;
    }

    public CartResponse getCartByUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        return cartMapper.toCartResponse(cartRepository.findByUser(user)) ;
    }
}