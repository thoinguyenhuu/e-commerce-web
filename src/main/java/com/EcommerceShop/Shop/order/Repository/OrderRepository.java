package com.EcommerceShop.Shop.order.Repository;

import com.EcommerceShop.Shop.order.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,String> {
}
