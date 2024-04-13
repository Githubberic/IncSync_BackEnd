package com.websocket.handler;

import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.websocket.entity.DrawEventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Component
public class WebsocketEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketEventHandler.class);
    private final ObjectMapper objectMapper;

    @Autowired
    public WebsocketEventHandler(SocketIOServer server, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        server.addEventListener("draw", DrawEventData.class, (client, drawEventData, ackSender) -> {
            try {
                // Process the draw event data here
                int x1 = drawEventData.getX1();
                int y1 = drawEventData.getY1();
                int x2 = drawEventData.getX2();
                int y2 = drawEventData.getY2();
                String color = drawEventData.getColor();
                int thickness = drawEventData.getThickness();

                // Log the received data
                logger.info("Received draw event from client with coordinates: ({}, {}) to ({}, {})", x1, y1, x2, y2);

                String jsonData = this.objectMapper.writeValueAsString(drawEventData);

                // Calculate the size of the JSON string
                int dataSize = jsonData.getBytes().length;
                logger.info("Received draw event from client with data size: {} bytes", dataSize);

                // Broadcast the draw event data to all clients
                client.getNamespace().getBroadcastOperations().sendEvent("draw", drawEventData);
                logger.info("Broadcasted draw event to all clients");
            } catch (Exception e) {
                logger.error("Error handling draw event: {}", e.getMessage(), e);
            }
        });
    }
}