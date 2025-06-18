package com.EcommerceShop.Shop.Services.ServicesImpl;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CartItemResponse;
import com.EcommerceShop.Shop.Entity.Cart;
import com.EcommerceShop.Shop.Entity.CartItem;
import com.EcommerceShop.Shop.Entity.Product;
import com.EcommerceShop.Shop.Exception.AppException;
import com.EcommerceShop.Shop.Enums.ErrorCode;
import com.EcommerceShop.Shop.Mapper.CartItemMapper;
import com.EcommerceShop.Shop.Repository.CartItemRepository;
import com.EcommerceShop.Shop.Repository.CartRepository;
import com.EcommerceShop.Shop.Repository.ProductRepository;
import com.EcommerceShop.Shop.Repository.UserRepository;
import com.EcommerceShop.Shop.Services.CartItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemServiceImpl implements CartItemService {
    CartItemRepository cartItemRepository ;
    UserRepository userRepository ;
    CartRepository cartRepository ;
    ProductRepository productRepository ;
    CartItemMapper cartItemMapper ;
    @Override
    public CartItemResponse create(CartItemCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        Cart cart =  cartRepository.findByUser(userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED))) ;
        Product product = productRepository.findById(request.getProduct_id()).orElseThrow(() -> new AppException(ErrorCode.BAD_REQUEST)) ;

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .num(request.getNum())
                .product(product).build();

        return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItem)) ;

    }
}
