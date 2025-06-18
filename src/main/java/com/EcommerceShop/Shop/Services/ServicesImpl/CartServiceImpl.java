package com.EcommerceShop.Shop.Services.ServicesImpl;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CartResponse;
import com.EcommerceShop.Shop.Entity.Cart;
import com.EcommerceShop.Shop.Entity.User;
import com.EcommerceShop.Shop.Exception.AppException;
import com.EcommerceShop.Shop.Enums.ErrorCode;
import com.EcommerceShop.Shop.Mapper.CartMapper;
import com.EcommerceShop.Shop.Repository.CartRepository;
import com.EcommerceShop.Shop.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CartServiceImpl {
    CartRepository cartRepository ;
    UserRepository userRepository ;
    CartMapper cartMapper ;

    CartResponse create(CartItemCreateRequest request){
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        Cart cart = cartMapper.toCart(request) ;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;

        if(user.getCarts() == null){
            user.setCarts(new ArrayList<>());
        }

        user.getCarts().add(cart) ;
        return null ;
    }
}
