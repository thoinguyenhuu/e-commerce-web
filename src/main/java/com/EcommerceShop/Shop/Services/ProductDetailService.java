package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.ProductDetailRequest;
import com.EcommerceShop.Shop.Repository.ProductDetailRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailService {
    ProductDetailRepository productDetailRepository ;


    public void create(ProductDetailRequest request){

    }
}
