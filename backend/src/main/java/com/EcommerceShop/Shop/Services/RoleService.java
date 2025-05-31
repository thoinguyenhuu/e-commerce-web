package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.response.RoleResponse;
import com.EcommerceShop.Shop.Entity.Role;
import com.EcommerceShop.Shop.Repository.RoleRepository;
import jakarta.persistence.ForeignKey;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository repository ;
    public List<Role> getAllRole(){
        return repository.findAll() ;
    }
    public List<Role> getRoles(List<String> name){
        return repository.findByNameIn(name) ;
    }

    public Role getRole(String name){
        return repository.findByName(name) ;
    }

}
