package com.EcommerceShop.Shop.user;

import com.EcommerceShop.Shop.user.dto.request.UserCreationRequest;
import com.EcommerceShop.Shop.user.dto.request.UserUpdateRequest;
import com.EcommerceShop.Shop.user.Entity.User;
import com.EcommerceShop.Shop.user.dto.response.UserResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toUser(UserCreationRequest request) ;

    UserResponse toUserResponse(User user) ;

    void updateUser(@MappingTarget User user , UserUpdateRequest request) ;



}
