package com.scheduler.app.backend.Messaging.Board;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
// use for board
@Configuration
@EnableWebSocket
public class WebSocketConfigRaw implements WebSocketConfigurer{

    private final WebSocketHandlerRaw rawHandler;

    public WebSocketConfigRaw(WebSocketHandlerRaw rawHandler) {
        this.rawHandler = rawHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(rawHandler, "/board-ws").setAllowedOrigins("*");
    }
}
