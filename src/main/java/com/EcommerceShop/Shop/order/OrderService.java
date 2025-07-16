package com.EcommerceShop.Shop.order;

import com.EcommerceShop.Shop.GiaoHangNhanh.GHNService;
import com.EcommerceShop.Shop.GiaoHangNhanh.dto.request.ShippingFeeRequest;
import com.EcommerceShop.Shop.exception.AppException;
import com.EcommerceShop.Shop.exception.ErrorCode;
import com.EcommerceShop.Shop.order.Entity.OrderItem;
import com.EcommerceShop.Shop.order.Entity.OrderStatus;
import com.EcommerceShop.Shop.order.Entity.Orders;
import com.EcommerceShop.Shop.order.dto.request.OrderItemRequest;
import com.EcommerceShop.Shop.order.dto.request.OrderRequest;
import com.EcommerceShop.Shop.order.dto.response.OrderItemResponse;
import com.EcommerceShop.Shop.order.dto.response.OrderNotify;
import com.EcommerceShop.Shop.order.dto.response.OrderResponse;
import com.EcommerceShop.Shop.order.Repository.OrderItemRepository;
import com.EcommerceShop.Shop.order.Repository.OrderRepository;
import com.EcommerceShop.Shop.order.dto.response.PreviewOrderResponse;
import com.EcommerceShop.Shop.order.mapper.OrderMapper;
import com.EcommerceShop.Shop.product.Entity.Product;
import com.EcommerceShop.Shop.product.Entity.ProductDetail;
import com.EcommerceShop.Shop.product.ProductMapper;
import com.EcommerceShop.Shop.product.dto.response.ProductDetailResponse;
import com.EcommerceShop.Shop.product.repository.ProductDetailRepository;
import com.EcommerceShop.Shop.product.repository.ProductRepository;
import com.EcommerceShop.Shop.user.Entity.User;
import com.EcommerceShop.Shop.user.UserRepository;
import com.EcommerceShop.Shop.util.config.WebSocketConfig;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderService {
    SimpMessagingTemplate messagingTemplate ;
    OrderItemRepository orderItemRepository;
    OrderRepository orderRepository;
    ProductDetailRepository productDetailRepository;
    GHNService ghnService ;
    UserRepository userRepository ;
    OrderMapper orderMapper ;



    @Transactional
    public OrderResponse createOrderItem(OrderRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException( ErrorCode.BAD_REQUEST)) ;
        PreviewOrderResponse response = preview(request) ;
        Double total = response.getTotal() ;
        Orders orders = Orders.builder()
                .createdAt(new Date())
                .status(OrderStatus.PENDING).build();
        List<OrderItem> orderItems = request.getItems().stream().map(orderItemRequest ->
                OrderItem.builder()
                        .num(orderItemRequest.getNum())
                        .orders(orders)
                        .item(productDetailRepository.findById(orderItemRequest.getDetailId()).orElseThrow(
                                ()-> new AppException(ErrorCode.ITEM_NOT_EXIST))).build()).toList() ;
        orders.setOrderItems(orderItems);
        orders.setUser(user);
        orderRepository.save(orders) ;
        OrderNotify orderNotify = OrderNotify.builder()
                .message(String.format("Có đơn hàng mới được tạo từ %s, giá trị $%.2f",
                        user.getFirstName() + " " + user.getLastName(), total))
                .orderId(orders.getId()).build();
        messagingTemplate.convertAndSend("/topic/admin", orderNotify);
        return orderMapper.toOrderResponse(orders) ;
    }

    public PreviewOrderResponse preview(OrderRequest request){
        Set<Long> detailIds = request.getItems().stream().map(OrderItemRequest::getDetailId).collect(Collectors.toSet());

        List<ProductDetail> productDetails = productDetailRepository.findAllById(detailIds) ;
        if(productDetails.isEmpty())
            throw new AppException(ErrorCode.ITEM_NOT_EXIST) ;
        Map<Long, ProductDetail> detailMap = productDetails.stream().collect(Collectors.toMap(ProductDetail::getId, Function.identity())) ;
        Map<ProductDetail,Long> items  = request.getItems().stream()
                .collect(
                        Collectors
                                .toMap(orderItemRequest -> detailMap.get(orderItemRequest.getDetailId()),
                                        OrderItemRequest::getNum)
                ) ;
        Double subTotal = items.entrySet().stream().mapToDouble(
                value -> {
                    ProductDetail detail = value.getKey() ;
                    Long num = value.getValue() ;
                    return detail.getPrice()*num ;
                }
        ).sum() ;

        Long shippingFee = ghnService.calculateFee(ShippingFeeRequest.builder()
                .to_district_id(request.getShippingAddress().getDistrictId())
                        .to_ward_code(request.getShippingAddress().getWardId().toString())

                .build()) ;

        Double total = subTotal + shippingFee ;
        return PreviewOrderResponse.builder()
                .subTotal(subTotal)
                .shippingFee(shippingFee)
                .total(total).build();

    }


    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrderItem(String orderItemId){
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_EXIST)) ;
        Orders orders = orderItem.getOrders() ;
        if(!orders.getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            throw new AppException(ErrorCode.UNAUTHORIZED) ;
        }
        orders.getOrderItems().remove(orderItem) ;
        if(orders.getOrderItems().isEmpty()){
            orderRepository.delete(orders);
        }
        else {
            orderRepository.save(orders) ;
        }
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponse updateOrderStatus(String orderId){
        Orders orders = orderRepository.findById(orderId).orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_FOUND)) ;
        orders.setStatus(orders.getStatus().nextStatus());
        return orderMapper.toOrderResponse(orderRepository.save(orders)) ;
    }

    public List<OrderStatus> getListOrderStatus(){
        return Arrays.stream(OrderStatus.values()).toList() ;
    }

    public OrderResponse getOrder(String orderId){
        return orderMapper.toOrderResponse(orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND))) ;
    }

    public List<OrderResponse> getAllOrder(){
        return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList() ;
    }
}
