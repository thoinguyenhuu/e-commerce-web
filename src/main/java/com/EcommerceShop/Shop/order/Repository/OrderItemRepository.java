package com.EcommerceShop.Shop.order.Repository;

import com.EcommerceShop.Shop.order.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
}
