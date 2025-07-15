package com.EcommerceShop.Shop.util.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // ✅ Đúng endpoint + CORS cho mọi origin
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // Cho phép frontend từ mọi host
                .withSockJS();                 // Bắt buộc nếu dùng SockJS từ client
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // nơi để gửi message về cho client
        config.setApplicationDestinationPrefixes("/app"); // nơi client gửi message tới server
    }
}

