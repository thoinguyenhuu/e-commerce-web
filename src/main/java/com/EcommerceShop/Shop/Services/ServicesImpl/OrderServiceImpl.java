package com.EcommerceShop.Shop.Services.ServicesImpl;

import com.EcommerceShop.Shop.DTO.request.OrderItemRequest;
import com.EcommerceShop.Shop.DTO.response.OrderItemResponse;
import com.EcommerceShop.Shop.Services.OrderItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    public OrderItemResponse createOrderItem(OrderItemRequest request) {
        return null;
    }
}
