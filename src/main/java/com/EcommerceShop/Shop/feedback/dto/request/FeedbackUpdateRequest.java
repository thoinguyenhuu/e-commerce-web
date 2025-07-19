package com.EcommerceShop.Shop.feedback.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FeedbackUpdateRequest {
    private String comment ;
    private Integer rate ;
}
