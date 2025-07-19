package com.EcommerceShop.Shop.feedback.dto.response;

import com.EcommerceShop.Shop.order.dto.response.OrderItemResponse;
import com.EcommerceShop.Shop.user.dto.response.UserResponse;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class FeedbackDTO {
    private String id ;
    private String comment ;
    private Integer rate ;
    private UserResponse user ;
    private OrderItemResponse orderItem ;
}
