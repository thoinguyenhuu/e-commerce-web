package com.EcommerceShop.Shop.Mapper;

import com.EcommerceShop.Shop.DTO.request.UserCreationRequest;
import com.EcommerceShop.Shop.DTO.request.UserUpdateRequest;
import com.EcommerceShop.Shop.DTO.response.UserResponse;
import com.EcommerceShop.Shop.Entity.User;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toUser(UserCreationRequest request) ;

    UserResponse toUserResponse(User user) ;

    void updateUser(@MappingTarget User user , UserUpdateRequest request) ;



}
