package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.UserCreationRequest;
import com.EcommerceShop.Shop.DTO.request.UserUpdateRequest;
import com.EcommerceShop.Shop.DTO.response.UserResponse;


import java.util.HashSet;
import java.util.List;

public interface UserService {
    UserResponse createUser(UserCreationRequest request) ;

    UserResponse updateUser(String id, UserUpdateRequest request) ;

    List<UserResponse> getAllUsers() ;

    UserResponse getMyInfo(String id) ;
    void deleteUser(String userId) ;
}
