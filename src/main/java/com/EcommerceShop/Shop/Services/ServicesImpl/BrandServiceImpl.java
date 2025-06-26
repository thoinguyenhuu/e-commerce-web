package com.EcommerceShop.Shop.Services.ServicesImpl;

import com.EcommerceShop.Shop.DTO.request.BrandCreateRequest;
import com.EcommerceShop.Shop.DTO.response.BrandResponse;
import com.EcommerceShop.Shop.Entity.Brand;
import com.EcommerceShop.Shop.Repository.BrandRepository;
import com.EcommerceShop.Shop.Services.BrandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandServiceImpl implements BrandService {
    BrandRepository brandRepository ;
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public BrandResponse create(BrandCreateRequest request) {
        Brand brand = Brand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .build();
        brand = brandRepository.save(brand) ;
        return BrandResponse.builder()
                .id(brand.getId())
                .description(brand.getDescription())
                .logoUrl(brand.getLogoUrl())
                .name(brand.getName())
                .build();
    }

    @Override
    public List<BrandResponse> getListBrand() {
        return brandRepository.findAll().stream().map(brand -> BrandResponse.builder()
                .name(brand.getName())
                .id(brand.getId())
                .logoUrl(brand.getLogoUrl())
                .description(brand.getDescription()).build()).toList() ;
    }
}
