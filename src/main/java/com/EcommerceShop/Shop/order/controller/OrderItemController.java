package com.EcommerceShop.Shop.order.controller;

import com.EcommerceShop.Shop.order.OrderService;
import com.EcommerceShop.Shop.order.dto.request.OrderRequest;
import com.EcommerceShop.Shop.order.dto.response.PreviewOrderResponse;
import com.EcommerceShop.Shop.util.ApiResponseWrapper;
import com.EcommerceShop.Shop.order.dto.response.OrderResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderItemController {
    OrderService orderService;

    @PostMapping
    ApiResponseWrapper<OrderResponse> createOrderItem(@RequestBody OrderRequest request){
        return ApiResponseWrapper.<OrderResponse>builder()
                .data(orderService.createOrderItem(request)).build();
    }

    @PostMapping("/preview")
    ApiResponseWrapper<PreviewOrderResponse> previewTotalFee(@RequestBody OrderRequest request){
        return ApiResponseWrapper.<PreviewOrderResponse>builder()
                .data(orderService.preview(request)).build();
    }


}
