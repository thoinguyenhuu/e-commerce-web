package com.EcommerceShop.Shop.Exception;

import lombok.*;

@Getter
@Setter
public class AppException extends RuntimeException {
    private final ErrorCode errorCode ;
    public AppException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode ;
    }
}
