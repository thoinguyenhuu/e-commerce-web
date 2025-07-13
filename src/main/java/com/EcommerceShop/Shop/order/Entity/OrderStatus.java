package com.EcommerceShop.Shop.order.Entity;

public enum OrderStatus {
    PENDING,          // Đơn hàng vừa được tạo, đang chờ xác nhận
    CONFIRMED,       // Đơn hàng đã được xác nhận (bởi hệ thống hoặc admin)
    PROCESSING,      // Đang xử lý (chuẩn bị hàng, đóng gói)
    SHIPPED,         // Đã giao cho đơn vị vận chuyển
    OUT_FOR_DELIVERY,// Đang giao hàng đến khách
    DELIVERED,       // Đã giao hàng thành công
    CANCELLED,       // Đơn hàng bị hủy (bởi khách hàng hoặc hệ thống)
    RETURN_REQUESTED,// Yêu cầu trả hàng
    RETURNED,        // Đã trả hàng thành công
    REFUNDED         // Đã hoàn tiền

}
