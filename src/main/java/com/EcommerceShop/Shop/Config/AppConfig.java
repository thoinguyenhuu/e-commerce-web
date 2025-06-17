package com.EcommerceShop.Shop.Config;

import com.EcommerceShop.Shop.Entity.User;
import com.EcommerceShop.Shop.Enums.Role;
import com.EcommerceShop.Shop.Repository.UserRepository;
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
    final String admin_username = "ADMIN" ;
    final String admin_password = "ADMIN" ;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.findByUsername(admin_username).isEmpty()){
                User admin = User.builder()
                        .username(admin_username)
                        .password(passwordEncoder().encode(admin_password))
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(admin) ;
            }
        } ;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10) ;
    }

}
