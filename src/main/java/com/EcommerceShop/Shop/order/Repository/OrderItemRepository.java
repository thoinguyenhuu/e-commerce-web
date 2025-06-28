package com.EcommerceShop.Shop.order.Repository;

import com.EcommerceShop.Shop.order.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
}
