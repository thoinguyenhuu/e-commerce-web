package com.EcommerceShop.Shop.GiaoHangNhanh.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WardResponse {
    private Long WardCode ;
    private Long DistrictID ;
    private String WardName ;
}
