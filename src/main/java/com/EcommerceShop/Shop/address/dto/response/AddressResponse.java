package com.EcommerceShop.Shop.address.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressResponse {
    private Long id ;
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
