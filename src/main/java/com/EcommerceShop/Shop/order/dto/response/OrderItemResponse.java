package com.EcommerceShop.Shop.order.dto.response;

import com.EcommerceShop.Shop.product.dto.response.ProductDetailResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemResponse {
    private String id ;

    private ProductDetailResponse item ;

    private Integer num ;

}
