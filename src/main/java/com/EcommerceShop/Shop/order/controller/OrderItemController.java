package com.EcommerceShop.Shop.order.controller;

import com.EcommerceShop.Shop.order.Entity.OrderStatus;
import com.EcommerceShop.Shop.order.OrderService;
import com.EcommerceShop.Shop.order.dto.request.OrderRequest;
import com.EcommerceShop.Shop.order.dto.request.UpdateStatusRequest;
import com.EcommerceShop.Shop.order.dto.response.PreviewOrderResponse;
import com.EcommerceShop.Shop.util.ApiResponseWrapper;
import com.EcommerceShop.Shop.order.dto.response.OrderResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{orderItemId}")
    ApiResponseWrapper<?> deleteOrderItem(@PathVariable String orderItemId){
        orderService.deleteOrderItem(orderItemId);
        return ApiResponseWrapper.builder()
                .message("Order has been deleted!").build();
    }

    @GetMapping("/{orderId}")
    ApiResponseWrapper<OrderResponse> getOrder(@PathVariable String orderId){
        return ApiResponseWrapper.<OrderResponse>builder()
                .data(orderService.getOrder(orderId)).build();
    }

    @PatchMapping("/{orderId}/update-status")
    ApiResponseWrapper<OrderResponse> nextStatus(@PathVariable String orderId,
                                                 @RequestBody UpdateStatusRequest request
                                                 ){
        return ApiResponseWrapper.<OrderResponse>builder()
                .data(orderService.updateOrderStatus(orderId,request)).build();
    }

    @GetMapping("/status")
    ApiResponseWrapper<List<OrderStatus>> getOrderStatus(){
        return ApiResponseWrapper.<List<OrderStatus>>builder()
                .data(orderService.getListOrderStatus()).build() ;
    }

    @GetMapping("/all")
    ApiResponseWrapper<List<OrderResponse>> getAllOrder(){
        return ApiResponseWrapper.<List<OrderResponse>>builder()
                .data(orderService.getAllOrder()).build();
    }

}
