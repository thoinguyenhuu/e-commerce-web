package com.EcommerceShop.Shop.user;

import com.EcommerceShop.Shop.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username) ;
}
