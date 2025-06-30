package com.EcommerceShop.Shop.address.controller;


import com.EcommerceShop.Shop.address.service.AddressService;
import com.EcommerceShop.Shop.address.dto.request.AddressRequest;
import com.EcommerceShop.Shop.address.dto.response.AddressResponse;
import com.EcommerceShop.Shop.util.ApiResponseWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/address")
public class AddressController {
    AddressService addressService ;

    @PostMapping
    ApiResponseWrapper<AddressResponse> createAddress(@PathVariable String userId, @RequestBody AddressRequest request){
        return ApiResponseWrapper.<AddressResponse>builder()
                .data(addressService.createAddress(userId,request))
                .build();
    }
    @GetMapping
    ApiResponseWrapper<List<AddressResponse>> getAddresses(@PathVariable String userId){
        return ApiResponseWrapper.<List<AddressResponse>>builder()
                .data(addressService.getAll(userId)).build();
    }

    @PutMapping("/{addressId}")
    ApiResponseWrapper<AddressResponse> updateAddress(@PathVariable String userId, @PathVariable String addressId, @RequestBody AddressRequest request){
        return ApiResponseWrapper.<AddressResponse>builder()
                .data(addressService.update(userId,addressId,request)).build();
    }
}
