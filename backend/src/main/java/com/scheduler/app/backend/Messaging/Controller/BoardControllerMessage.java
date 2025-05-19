package com.scheduler.app.backend.Messaging.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.scheduler.app.backend.Messaging.Board.Models.BoardInput;
import com.scheduler.app.backend.Messaging.Models.BoardTask;

@Controller
public class BoardControllerMessage{
    // server message to client
    @SendTo("/commands")
    public BoardTask commandSent(BoardTask task) throws Exception{
        String convert=task.toString();
        return task;
    }
    // client send back to server
    @MessageMapping("/response")
    public void responseBack(BoardInput input){
        
    }
}
