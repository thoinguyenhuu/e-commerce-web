package com.EcommerceShop.Shop.Mapper;

import com.EcommerceShop.Shop.DTO.request.UserCreationRequest;
import com.EcommerceShop.Shop.DTO.request.UserUpdateRequest;
import com.EcommerceShop.Shop.DTO.response.UserResponse;
import com.EcommerceShop.Shop.Entity.Role;
import com.EcommerceShop.Shop.Entity.User;
import com.EcommerceShop.Shop.Entity.UserRole;
import jdk.jfr.Name;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toUser(UserCreationRequest request) ;

    @Mapping(source = "userRoles", target = "role", qualifiedByName = "mapRoles")
    UserResponse toUserResponse(User user) ;

    void updateUser(@MappingTarget User user , UserUpdateRequest request) ;

    @Named(value = "mapRoles")
    default Set<String> mapRoles(Set<UserRole> userRoles) {
        return userRoles.stream()
                .map(userRole -> userRole.getRole().getName())  // giả sử Role có trường name
                .collect(Collectors.toSet());
    }

}
