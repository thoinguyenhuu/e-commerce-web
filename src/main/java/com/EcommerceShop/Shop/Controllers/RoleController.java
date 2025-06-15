package com.EcommerceShop.Shop.Controllers;

import com.EcommerceShop.Shop.DTO.response.ApiResponse;
import com.EcommerceShop.Shop.Entity.Role;
import com.EcommerceShop.Shop.Services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    RoleService roleService ;

    @GetMapping("/all")
    public ApiResponse<List<Role>> getAllRoles(){
        return ApiResponse.<List<Role>>builder()
                .result(roleService.getAllRole()).build();
    }

}
