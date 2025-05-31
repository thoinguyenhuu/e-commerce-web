package com.EcommerceShop.Shop.Config;

import com.EcommerceShop.Shop.Entity.Role;
import com.EcommerceShop.Shop.Entity.User;
import com.EcommerceShop.Shop.Entity.UserRole;
import com.EcommerceShop.Shop.Repository.RoleRepository;
import com.EcommerceShop.Shop.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class AppConfig {
    final String[] nameRoles = {"ADMIN", "USER", "SELLER" , "SHIPPER"} ;
    final String admin_username = "ADMIN" ;
    final String admin_password = "ADMIN" ;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            for (String name : nameRoles) {
                if (roleRepository.findByName(name) == null) {
                    roleRepository.save(Role.builder()
                            .name(name)
                            .userRoles(new HashSet<>()).build()) ;
                }
            }
            if(userRepository.findByUsername(admin_username).isEmpty()){
                User admin = User.builder()
                        .username(admin_username)
                        .password(passwordEncoder().encode(admin_password))
                        .userRoles(new HashSet<>())
                        .build();
                Role role = roleRepository.findByName("ADMIN") ;
                UserRole userRole = UserRole.builder()
                        .user(admin)
                        .role(role).build();
                admin.getUserRoles().add(userRole) ;
                roleRepository.save(role) ;
                userRepository.save(admin) ;
            }
        } ;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10) ;
    }

}
