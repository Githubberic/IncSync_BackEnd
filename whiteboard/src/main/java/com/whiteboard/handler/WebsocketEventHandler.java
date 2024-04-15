package com.whiteboard.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whiteboard.entity.Stroke;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebsocketEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketEventHandler.class);
    private final ObjectMapper objectMapper;
    private final SocketIOServer server;

    @Autowired
    public WebsocketEventHandler(SocketIOServer server, ObjectMapper objectMapper) {
        this.server = server;
        this.objectMapper = objectMapper;

        // Event handler for "draw" event
        server.addEventListener("draw", Stroke.class, this::handleDraw);

        // Event handler for "joinRoom" event
        server.addEventListener("joinRoom", String.class, this::handleJoinRoom);

        // Event handler for "leaveRoom" event
        server.addEventListener("leaveRoom", String.class, this::handleLeaveRoom);
    }

    private void handleDraw(SocketIOClient client, Stroke drawEventData, AckRequest ackSender) {
        try {
            // Process the draw event data here
            int x1 = drawEventData.getX1();
            int y1 = drawEventData.getY1();
            int x2 = drawEventData.getX2();
            int y2 = drawEventData.getY2();
            String color = drawEventData.getColor();
            int thickness = drawEventData.getThickness();
            String whiteboardId = drawEventData.getWhiteboardId();

            // Log the received data
            logger.info("Received draw event from client with coordinates: ({}, {}) to ({}, {}) on whiteboard: {}", x1, y1, x2, y2, whiteboardId);


            // Broadcast the draw event data to all clients in the same room (whiteboard)
            client.getNamespace().getRoomOperations(whiteboardId).sendEvent("draw", drawEventData);
            logger.info("Broadcasted draw event to all clients on whiteboard: {}", whiteboardId);
        } catch (Exception e) {
            logger.error("Error handling draw event: {}", e.getMessage(), e);
        }
    }

    private void handleJoinRoom(SocketIOClient client, String whiteboardId, AckRequest ackRequest) {
        logger.info("Client with session ID {} joined room: {}", client.getSessionId(), whiteboardId);
        client.joinRoom(whiteboardId);
    }

    private void handleLeaveRoom(SocketIOClient client, String whiteboardId, AckRequest ackRequest) {
        logger.info("Client with session ID {} left room: {}", client.getSessionId(), whiteboardId);
        client.leaveRoom(whiteboardId);
    }
}
