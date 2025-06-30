package com.EcommerceShop.Shop.address.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressRequest {
    private String province ;
    private Long province_id ;
    private String district ;
    private Long district_id ;
    private String ward ;
    private Long ward_id ;
    private String info ;
    private String phone ;
    private String receiverName ;
    private boolean isDefault ;
}
