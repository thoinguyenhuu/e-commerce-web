package com.EcommerceShop.Shop.order.dto.response;


import com.EcommerceShop.Shop.order.Entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse {

    private String id ;

    private String userId ;

    private OrderStatus status ;

    private Date createdAt ;

    private List<OrderItemResponse> orderItems;

    private Double total ;

}
