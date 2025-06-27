package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.OrderItemRequest;
import com.EcommerceShop.Shop.DTO.response.OrderItemResponse;

public interface OrderItemService {

    OrderItemResponse createOrderItem(OrderItemRequest request) ;
}
