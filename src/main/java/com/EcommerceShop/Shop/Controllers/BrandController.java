package com.EcommerceShop.Shop.Controllers;


import com.EcommerceShop.Shop.DTO.request.BrandCreateRequest;
import com.EcommerceShop.Shop.DTO.response.ApiResponseWrapper;
import com.EcommerceShop.Shop.DTO.response.BrandResponse;
import com.EcommerceShop.Shop.Services.BrandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandController {
    BrandService brandService ;

    @PostMapping
    ApiResponseWrapper<BrandResponse> createBrand(@RequestBody BrandCreateRequest request){
        return ApiResponseWrapper.<BrandResponse>builder()
                .result(brandService.create(request)).build();
    }
}
