package com.EcommerceShop.Shop.order;

import com.EcommerceShop.Shop.GiaoHangNhanh.GHNService;
import com.EcommerceShop.Shop.GiaoHangNhanh.dto.request.ShippingFeeRequest;
import com.EcommerceShop.Shop.order.dto.request.OrderItemRequest;
import com.EcommerceShop.Shop.order.dto.request.OrderRequest;
import com.EcommerceShop.Shop.order.dto.response.OrderResponse;
import com.EcommerceShop.Shop.order.Repository.OrderItemRepository;
import com.EcommerceShop.Shop.order.Repository.OrderRepository;
import com.EcommerceShop.Shop.order.dto.response.PreviewOrderResponse;
import com.EcommerceShop.Shop.product.Entity.Product;
import com.EcommerceShop.Shop.product.Entity.ProductDetail;
import com.EcommerceShop.Shop.product.ProductMapper;
import com.EcommerceShop.Shop.product.repository.ProductDetailRepository;
import com.EcommerceShop.Shop.product.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderService {
    ProductRepository productRepository ;
    OrderItemRepository orderItemRepository;
    OrderRepository orderRepository;
    ProductDetailRepository productDetailRepository;
    GHNService ghnService ;
    public OrderResponse createOrderItem(OrderRequest request) {
        return null;
    }

    public PreviewOrderResponse preview(OrderRequest request){
        Set<Long> detailIds = request.getItems().stream().map(OrderItemRequest::getDetailId).collect(Collectors.toSet());

        List<ProductDetail> productDetails = productDetailRepository.findAllById(detailIds) ;
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
                .to_district_id(request.getShippingAddress().getDistrict_id())
                        .to_ward_code(request.getShippingAddress().getWard_id().toString())

                .build()) ;

        Double total = subTotal + shippingFee ;
        return PreviewOrderResponse.builder()
                .subTotal(subTotal)
                .shippingFee(shippingFee)
                .total(total).build();

    }
}
