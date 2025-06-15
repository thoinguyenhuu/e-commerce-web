package com.EcommerceShop.Shop.Exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    USER_NOT_EXISTED("User not existed!", HttpStatus.NOT_FOUND),
    USER_EXISTED("User existed!", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED("Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("Unauthorized!", HttpStatus.FORBIDDEN),
    BAD_REQUEST("Bad Request", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN("Invalid Token", HttpStatus.BAD_REQUEST),
    CATEGORY_EXISTED("Category existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND("Category not found", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND("Product not found", HttpStatus.BAD_REQUEST)
    ;
    ErrorCode(String message, HttpStatus httpStatus){
        this.code = httpStatus.value();
        this.message = message ;
        this.httpStatus = httpStatus ;
    }

    int code ;
    String message ;
    HttpStatus httpStatus ;
}
