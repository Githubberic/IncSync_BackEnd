package com.whiteboard.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private SocketIOServer server;

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(hostname);
        config.setPort(port);
        config.setOrigin("*");

        server = new SocketIOServer(config);
        return server;
    }

    @Bean
    public ServerStarter serverStarter() {
        return new ServerStarter();
    }

    private class ServerStarter {

        public ServerStarter() {
            startServer();
        }

        private void startServer() {
            server.start();
            logger.info("WebSocket server started successfully!");
        }
    }
}

