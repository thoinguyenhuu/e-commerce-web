package com.EcommerceShop.Shop.order.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PreviewOrderResponse {
    private Long shippingFee ;
    private Double subTotal ;
    private Double discount ;
    private Double total ;
}
