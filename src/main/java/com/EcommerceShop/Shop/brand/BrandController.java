package com.EcommerceShop.Shop.brand;


import com.EcommerceShop.Shop.util.ApiResponseWrapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandController {
    BrandService brandService ;

    @PostMapping
    ApiResponseWrapper<BrandResponse> createBrand(@RequestBody BrandRequest request){
        return ApiResponseWrapper.<BrandResponse>builder()
                .data(brandService.create(request)).build();
    }

    @GetMapping("/all")
    ApiResponseWrapper<BrandResponse> getAllBrand(){
        return ApiResponseWrapper.<BrandResponse>builder().build() ;
    }

    @PutMapping("/{brandId}")
    ApiResponseWrapper<BrandResponse> updateBrand(@PathVariable String brandId, BrandRequest request){
        return ApiResponseWrapper.<BrandResponse>builder()
                .data(brandService.updateBrand(brandId,request)).build();
    }

    @DeleteMapping("/{brandId}")
    ApiResponseWrapper<?> deleteBrand(@PathVariable String brandId){
        brandService.deleteBrand(brandId);
        return ApiResponseWrapper.builder()
                .code(200)
                .message("Brand has been deleted!").build();
    }
}
