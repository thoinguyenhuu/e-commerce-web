package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.BrandCreateRequest;
import com.EcommerceShop.Shop.DTO.response.BrandResponse;

import java.util.List;

public interface BrandService {
    BrandResponse create(BrandCreateRequest request) ;

    List<BrandResponse> getListBrand() ;
}
