package com.EcommerceShop.Shop.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    USER_NOT_EXISTED("User not existed!", HttpStatus.NOT_FOUND),
    USER_EXISTED("User existed!", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED("Unauthenticated! Xác thực thất bại", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("Unauthorized! Bạn không có quyền thực hiện thao tác này!", HttpStatus.FORBIDDEN),
    BAD_REQUEST("Bad Request", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN("Invalid Token!", HttpStatus.BAD_REQUEST),
    CATEGORY_EXISTED("Category existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND("Category not found", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND("Product not found", HttpStatus.NOT_FOUND),
    ITEM_NOT_EXIST("Item not existed!", HttpStatus.NOT_FOUND),
    BRAND_NOT_FOUND("Brand not found!", HttpStatus.NOT_FOUND),
    ADDRESS_ID_WRONG("AddressId wrong!", HttpStatus.NOT_FOUND),
    CLOUDINARY_ERROR("Lỗi up load lên cloudinary", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND("Lỗi không tìm thấy order", HttpStatus.NOT_FOUND),
    PASSWORD_WRONG("Password sai!", HttpStatus.BAD_REQUEST),
    CHUA_MUA_HANG("Bạn chưa mua hàng nên không thể đánh giá được", HttpStatus.BAD_REQUEST),
    DA_CO_FEEDBACK("Bạn đã có feedback cho product này rồi" , HttpStatus.BAD_REQUEST),
    FEEDBACK_KHONG_TON_TAI("Feedback không tồn tại", HttpStatus.BAD_REQUEST),
    SO_LUONG_SAN_PHAM_KHONG_DU("Số lượng sản phẩm không đủ", HttpStatus.BAD_REQUEST)
    ;
    ErrorCode(String message, HttpStatus httpStatus){
        this.code = httpStatus.value();
        this.message = message;
        this.httpStatus = httpStatus ;
    }

    int code ;
    String message;
    HttpStatus httpStatus ;
}
