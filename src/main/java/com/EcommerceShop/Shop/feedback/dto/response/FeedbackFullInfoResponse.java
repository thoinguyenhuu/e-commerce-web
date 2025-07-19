package com.EcommerceShop.Shop.feedback.dto.response;

import com.EcommerceShop.Shop.order.dto.response.OrderItemResponse;
import com.EcommerceShop.Shop.product.dto.response.ProductResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedbackFullInfoResponse extends FeedbackDTO {
    private ProductResponse productResponse ;
}
