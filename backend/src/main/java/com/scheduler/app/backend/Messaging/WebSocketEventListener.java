package com.scheduler.app.backend.Messaging;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    @EventListener
    private String handleSessionConnected(SessionConnectEvent event) {
        System.out.println(event);
        return "connect";
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        System.out.println("🔴 WebSocket connection closed");
    }
}
