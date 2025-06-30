package com.EcommerceShop.Shop.GiaoHangNhanh.controller;

import com.EcommerceShop.Shop.GiaoHangNhanh.GHNService;
import com.EcommerceShop.Shop.GiaoHangNhanh.dto.request.ShippingFeeRequest;
import com.EcommerceShop.Shop.GiaoHangNhanh.dto.response.FeeResponse;
import com.EcommerceShop.Shop.util.ApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping/fee")
public class ShippingFeeController {
    @Autowired
    private GHNService ghnService ;

    @PostMapping
    public ApiResponseWrapper<FeeResponse> getFee(@RequestBody ShippingFeeRequest request){
        return ApiResponseWrapper.<FeeResponse>builder()
                .data(ghnService.calculateFee(request)).build();
    }
}
