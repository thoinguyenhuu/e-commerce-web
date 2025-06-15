package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.Entity.Role;

import java.util.List;

public interface RoleService {
     List<Role> getAllRole() ;

     List<Role> getRoles(List<String> name) ;

     Role getRole(String name) ;
}
