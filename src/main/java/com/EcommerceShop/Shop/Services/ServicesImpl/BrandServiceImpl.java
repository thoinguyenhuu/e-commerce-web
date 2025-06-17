package com.EcommerceShop.Shop.Services.ServicesImpl;

import com.EcommerceShop.Shop.DTO.request.BrandCreateRequest;
import com.EcommerceShop.Shop.DTO.response.BrandResponse;
import com.EcommerceShop.Shop.Services.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Override
    public BrandResponse create(BrandCreateRequest request) {
        return null;
    }

    @Override
    public List<BrandResponse> getListBrand() {
        return List.of();
    }
}
