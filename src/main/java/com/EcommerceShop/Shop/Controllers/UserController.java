package com.EcommerceShop.Shop.Controllers;

import com.EcommerceShop.Shop.DTO.request.UserCreationRequest;
import com.EcommerceShop.Shop.DTO.request.UserUpdateRequest;
import com.EcommerceShop.Shop.DTO.response.ApiResponseWrapper;
import com.EcommerceShop.Shop.DTO.response.UserResponse;
import com.EcommerceShop.Shop.Services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService ;

    @GetMapping
    ApiResponseWrapper<List<UserResponse>> getAllUser(){
        return ApiResponseWrapper.<List<UserResponse>>builder()
                .result(userService.getAllUsers()).build();
    }
    @GetMapping("/me")
    ApiResponseWrapper<UserResponse> getMyInfo(){
        return ApiResponseWrapper.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }
    @PostMapping
    ApiResponseWrapper<UserResponse> createUser(@RequestBody UserCreationRequest request){
        return ApiResponseWrapper.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponseWrapper<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request){
        return ApiResponseWrapper.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }


    // Chưa test
    @DeleteMapping("/{userId}")
    ApiResponseWrapper<?> deleteUser(@PathVariable String userId){

        return ApiResponseWrapper.builder()
                .status(200)
                .message(String.format("User %s have been deleted", userId)).build();
    }
}
