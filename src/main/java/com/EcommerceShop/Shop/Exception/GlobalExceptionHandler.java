package com.EcommerceShop.Shop.Exception;

import com.EcommerceShop.Shop.DTO.response.ApiResponseWrapper;
import com.EcommerceShop.Shop.Enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponseWrapper<?>> handlingRuntimeException(RuntimeException e){
        log.info(Arrays.toString(e.getStackTrace())) ;
        log.info(e.getMessage());
        return  ResponseEntity.badRequest().body(ApiResponseWrapper.builder()
                .status(ErrorCode.BAD_REQUEST.getCode())
                .message(ErrorCode.BAD_REQUEST.getMessage()).build());
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponseWrapper<?>> handlingAccessDeniedException(AccessDeniedException e){
        return ResponseEntity.status(ErrorCode.UNAUTHORIZED.getCode())
                .body(ApiResponseWrapper.builder()
                        .status(ErrorCode.UNAUTHORIZED.getCode())
                        .message(e.getMessage()).build()) ;
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponseWrapper<?>> handlingAppException(AppException exception){
        return ResponseEntity.status(exception.getErrorCode().getCode())
                .body(ApiResponseWrapper.builder()
                        .status(exception.getErrorCode().getCode())
                        .message(exception.getMessage()).build()) ;
    }

}
