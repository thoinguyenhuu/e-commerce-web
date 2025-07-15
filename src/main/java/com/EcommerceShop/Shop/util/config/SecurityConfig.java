package com.EcommerceShop.Shop.util.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    String[] public_endpoint_post = {
            "/user", "/auth/**"
    };
    String[] public_endpoint_get = {
            "/product/**",
            "/category/all",
            "/brand/all",
            "/address"
    };
    String[] all_endpoint = {
            "/swagger-ui/**",
            "/v3/**",
            "/swagger-ui.html"
    };
    CustomJwtDecoder customJwtDecoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/swagger-ui/**", "/v3/**", "/swagger-ui.html").permitAll()
                                .requestMatchers(HttpMethod.POST, public_endpoint_post).permitAll()
                                .requestMatchers(HttpMethod.GET, public_endpoint_get).permitAll()
                                .requestMatchers("/shop-api/ws/**").permitAll() // Cho phép tất cả yêu cầu WebSocket
                                .anyRequest().authenticated())
                .csrf().disable()
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                                .decoder(customJwtDecoder)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));

        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("role");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*","http://127.0.0.1:5500")); // Cho phép tất cả origin
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        // Bỏ setAllowCredentials(true) để tương thích với allowedOriginPatterns("*")
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}