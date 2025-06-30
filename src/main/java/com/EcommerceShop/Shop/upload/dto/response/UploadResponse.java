package com.EcommerceShop.Shop.upload.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UploadResponse {
    private String  ownerType ;
    private String ownerId ;

    private String url ;

    private Long uploadId ;
}
