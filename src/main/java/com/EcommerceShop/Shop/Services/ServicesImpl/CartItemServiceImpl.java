package com.EcommerceShop.Shop.Services.ServicesImpl;

import com.EcommerceShop.Shop.DTO.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CartItemResponse;
import com.EcommerceShop.Shop.Entity.*;
import com.EcommerceShop.Shop.Exception.AppException;
import com.EcommerceShop.Shop.Enums.ErrorCode;
import com.EcommerceShop.Shop.Mapper.CartItemMapper;
import com.EcommerceShop.Shop.Repository.CartItemRepository;
import com.EcommerceShop.Shop.Repository.CartRepository;
import com.EcommerceShop.Shop.Repository.ProductRepository;
import com.EcommerceShop.Shop.Repository.UserRepository;
import com.EcommerceShop.Shop.Services.CartItemService;
import com.EcommerceShop.Shop.Services.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemServiceImpl implements CartItemService {

    CartItemRepository cartItemRepository ;
    UserRepository userRepository ;
    CartRepository cartRepository ;
    ProductRepository productRepository ;
    CartItemMapper cartItemMapper ;
    CartService cartService ;

    public CartItemResponse create(CartItemCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        Cart cart =  cartRepository.findByUser(userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED))) ;
        if(cart == null){
            cart = cartService.create() ;
        }
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_EXIST)) ;
        List<CartItem> cartItemList = cartItemRepository.findByCart(cart) ;
        Optional<CartItem> optionalCartItem = cartItemList.stream().filter(item -> item.getItem().getId().equals(request.getProductId())).findFirst();
        if(optionalCartItem.isPresent()){
            optionalCartItem.get().setNum(optionalCartItem.get().getNum() + request.getNum()) ;
            return cartItemMapper.toCartItemResponse(cartItemRepository.save(optionalCartItem.get())) ;
        }
        ProductDetail item = product.getProductDetails().stream().filter(productDetail -> productDetail.getId().equals(request.getDetailId())).findFirst().orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_EXIST)) ;
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .num(request.getNum())
                .item(item).build();
        cart.getCartItems().add(cartItem) ;
        return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItem)) ;
    }

    public void deleteCartItem(String itemId){
        CartItem item = cartItemRepository.findById(itemId).orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_EXIST)) ;
        cartItemRepository.delete(item);
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        Cart cart = cartRepository.findByUser(user) ;
        cart.getCartItems().remove(item) ;
    }

    @Override
    public CartItemResponse update(String id) {
        return null;
    }

    public List<CartItemResponse> getList(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        Cart cart = cartRepository.findByUser(user) ;
        return cartItemRepository.findByCart(cart).stream().map(cartItemMapper::toCartItemResponse).toList() ;
    }
}
