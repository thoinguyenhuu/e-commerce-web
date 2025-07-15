package com.EcommerceShop.Shop.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdatePasswordRequest {
    private String oldPassword ;
    private String newPassword ;

}
