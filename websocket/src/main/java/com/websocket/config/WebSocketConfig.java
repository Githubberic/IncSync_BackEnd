package com.websocket.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketConfig {

    @Value("${websocket.hostname}")
    private String hostname;

    @Value("${websocket.port}")
    private int port;

    @Bean
    @ConditionalOnMissingBean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(hostname);
        config.setPort(port); // Set the port you want to use
        config.setContext("/websocket");
        config.setOrigin("*"); // Set the allowed origin to "*"

        return new SocketIOServer(config);
    }
}
