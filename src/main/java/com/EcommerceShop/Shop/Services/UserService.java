package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.UserCreationRequest;
import com.EcommerceShop.Shop.DTO.request.UserUpdateRequest;
import com.EcommerceShop.Shop.DTO.response.UserResponse;


import java.util.HashSet;
import java.util.List;

public interface UserService {
    public UserResponse createUser(UserCreationRequest request) ;

    public UserResponse updateUser(String id, UserUpdateRequest request) ;

    public List<UserResponse> getAllUsers() ;

    public UserResponse getMyInfo(String id) ;
}
