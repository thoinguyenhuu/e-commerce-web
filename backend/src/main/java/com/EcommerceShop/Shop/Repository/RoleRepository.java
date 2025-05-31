package com.EcommerceShop.Shop.Repository;

import com.EcommerceShop.Shop.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name) ;
    List<Role> findByNameIn(List<String> name) ;

}
