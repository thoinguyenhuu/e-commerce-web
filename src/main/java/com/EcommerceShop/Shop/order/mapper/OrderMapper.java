package com.EcommerceShop.Shop.order.mapper;

import com.EcommerceShop.Shop.order.Entity.OrderItem;
import com.EcommerceShop.Shop.order.Entity.OrderStatus;
import com.EcommerceShop.Shop.order.Entity.Orders;
import com.EcommerceShop.Shop.order.dto.response.OrderItemResponse;
import com.EcommerceShop.Shop.order.dto.response.OrderResponse;
import com.EcommerceShop.Shop.product.ProductMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    @Autowired
    ProductMapper productMapper ;


    public OrderResponse toOrderResponse(Orders orders){
        return OrderResponse.builder()
                .total(orders.getTotal())
                .createdAt(orders.getCreatedAt())
                .id(orders.getId())
                .userId(orders.getUser().getId())
                .status(OrderStatus.PENDING)
                .orderItems(orders.getOrderItems().stream().map(
                        this::toOrderItemResponse
                ).toList()).build() ;
    }

    public OrderItemResponse toOrderItemResponse(OrderItem orderItem){
        return OrderItemResponse.builder()
                .item(productMapper.toProductDetailResponse(orderItem.getItem()))
                .id(orderItem.getId())
                .num(orderItem.getNum())
                .build();
    }
}
