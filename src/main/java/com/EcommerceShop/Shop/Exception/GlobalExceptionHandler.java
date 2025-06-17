package com.EcommerceShop.Shop.Exception;

import com.EcommerceShop.Shop.DTO.response.ApiResponse;
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
    ResponseEntity<ApiResponse<?>> handlingRuntimeException(RuntimeException e){
        log.info(Arrays.toString(e.getStackTrace())) ;
        return  ResponseEntity.badRequest().body(ApiResponse.builder()
                .status(ErrorCode.BAD_REQUEST.getCode())
                .message(ErrorCode.BAD_REQUEST.getMessage()).build());
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse<?>> handlingAccessDeniedException(AccessDeniedException e){
        return ResponseEntity.status(ErrorCode.UNAUTHORIZED.getCode())
                .body(ApiResponse.builder()
                        .status(ErrorCode.UNAUTHORIZED.getCode())
                        .message(e.getMessage()).build()) ;
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<?>> handlingAppException(AppException exception){
        return ResponseEntity.status(exception.getErrorCode().getCode())
                .body(ApiResponse.builder()
                        .status(exception.getErrorCode().getCode())
                        .message(exception.getMessage()).build()) ;
    }

}
