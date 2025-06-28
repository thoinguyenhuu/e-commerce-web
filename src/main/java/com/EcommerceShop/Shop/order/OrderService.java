package com.EcommerceShop.Shop.order;

import com.EcommerceShop.Shop.order.dto.request.OrderRequest;
import com.EcommerceShop.Shop.order.dto.response.OrderResponse;
import com.EcommerceShop.Shop.order.Repository.OrderItemRepository;
import com.EcommerceShop.Shop.order.Repository.OrderRepository;
import com.EcommerceShop.Shop.product.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderService {
    ProductRepository productRepository ;
    OrderItemRepository orderItemRepository;
    OrderRepository orderRepository;
    public OrderResponse createOrderItem(OrderRequest request) {
        return null;
    }
}
