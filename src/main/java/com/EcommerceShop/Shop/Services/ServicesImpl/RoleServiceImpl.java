package com.EcommerceShop.Shop.Services.ServicesImpl;

import com.EcommerceShop.Shop.Entity.Role;
import com.EcommerceShop.Shop.Repository.RoleRepository;
import com.EcommerceShop.Shop.Services.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
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
