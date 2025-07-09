package com.EcommerceShop.Shop.cart.Service;

import com.EcommerceShop.Shop.cart.Entity.Cart;
import com.EcommerceShop.Shop.cart.Entity.CartItem;
import com.EcommerceShop.Shop.cart.Mapper.CartItemMapper;
import com.EcommerceShop.Shop.cart.Repository.CartItemRepository;
import com.EcommerceShop.Shop.cart.Repository.CartRepository;
import com.EcommerceShop.Shop.cart.dto.request.CartItemCreateRequest;
import com.EcommerceShop.Shop.cart.dto.request.CartItemUpdateRequest;
import com.EcommerceShop.Shop.cart.dto.response.CartItemResponse;
import com.EcommerceShop.Shop.exception.AppException;
import com.EcommerceShop.Shop.exception.ErrorCode;
import com.EcommerceShop.Shop.product.Entity.Product;
import com.EcommerceShop.Shop.product.Entity.ProductDetail;
import com.EcommerceShop.Shop.product.repository.ProductDetailRepository;
import com.EcommerceShop.Shop.product.repository.ProductRepository;
import com.EcommerceShop.Shop.user.Entity.User;
import com.EcommerceShop.Shop.user.UserRepository;
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
public class CartItemService {

    CartItemRepository cartItemRepository ;
    UserRepository userRepository ;
    CartRepository cartRepository ;
    ProductRepository productRepository ;
    ProductDetailRepository productDetailRepository ;
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
        Optional<CartItem> optionalCartItem = cartItemList.stream().filter(item -> item.getItem().getId().equals(request.getDetailId())).findFirst();
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

    public void deleteCartItem(Long detailId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        User user  = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        Cart cart = user.getCarts() ;
        CartItem cartItem= cart.getCartItems().stream().filter(item -> item.getItem().getId().equals(detailId)).findFirst().orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_EXIST)) ;
        cart.getCartItems().remove(cartItem) ;
        cartItemRepository.delete(cartItem);
    }


    public CartItemResponse update(Long id, CartItemUpdateRequest request) {
        ProductDetail productDetail = productDetailRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_EXIST)) ;
        CartItem cartItem = cartItemRepository.findByItem(productDetail) ;
        if(request.getNum() != null){
            cartItem.setNum(request.getNum());
        }
        if (request.getDetailId() != null){
            cartItem.setItem(productDetailRepository.findById(request.getDetailId()).orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_EXIST))); ;
        }
        return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItem)) ;
    }

    public List<CartItemResponse> getList(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        Cart cart = cartRepository.findByUser(user) ;
        return cartItemRepository.findByCart(cart).stream().map(cartItemMapper::toCartItemResponse).toList() ;
    }
}
