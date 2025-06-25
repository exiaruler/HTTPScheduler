package com.scheduler.app.backend.Messaging.Board;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.app.backend.Messaging.Board.Models.BoardInput;
import com.scheduler.app.backend.Messaging.Models.BoardTask;
import com.scheduler.app.backend.Messaging.Models.BoardVariable;
import com.scheduler.app.backend.aREST.Models.Board;
import com.scheduler.app.backend.aREST.Service.BoardService;
@Component
public class WebSocketHandlerRaw extends TextWebSocketHandler{
    public final BoardService boardService;
    //public SchedulerTask scheduler;
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    public WebSocketHandlerRaw(BoardService boardService) {
        this.boardService = boardService;
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        String sessionId=session.getId();
        var headers = session.getHandshakeHeaders();
        String boardIdStr=headers.getFirst("board");
        String boardRamStr=headers.getFirst("ram-usage");
        String boardAct=headers.getFirst("action");
        if(boardIdStr!=""&&boardRamStr!=""){
            sessions.put(session.getId(),session);
            long boardId=Long.parseLong(boardIdStr);
            int ramUsed=Integer.parseInt(boardRamStr);
            System.out.println("board ID "+boardId);
            Board board=boardService.setWsConnection(boardId,sessionId,ramUsed);
            // board startup/power up
            if(boardAct.equals("startup")){
                BoardTask httpSche=new BoardTask();
                httpSche.setTask("schedule");
                httpSche.setMethod("requestconnection");
                httpSche.setDelayInterval(board.getPeriodicCheck());
                httpSche.setSystemTask(true);
                httpSche.setVariable(new BoardVariable());
                String json = objectToJson(httpSche);
                session.sendMessage(new TextMessage(json));
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received: " + payload);
        if(payload!=""){
            BoardInput boardIn=stringToObject(payload);
            long boardId=boardIn.getBoard();
            int ram=boardIn.getRamSpace();
            boardService.routineCheck(boardId,ram,"");
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("Client disconnected: " + session.getId());
        var headers = session.getHandshakeHeaders();
        String boardIdStr=headers.getFirst("board");
        String boardRamStr=headers.getFirst("ram-usage");
        if(boardIdStr!=""&&boardRamStr!=""){
            sessions.remove(session.getId());
            long boardId=Long.parseLong(boardIdStr);
            int ramUsed=Integer.parseInt(boardRamStr);
            boardService.setWsConnection(boardId,"",ramUsed);
        }
    }
    // convert board task to json string
    private String objectToJson(BoardTask task) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();  
        String json = mapper.writeValueAsString(task);
        return json;
    }
    // map board input to object
    private BoardInput stringToObject(String json) throws JsonMappingException, JsonProcessingException{
        BoardInput boardInput=null;
        ObjectMapper mapper = new ObjectMapper();
        boardInput=mapper.readValue(json,BoardInput.class);
        return boardInput;
    }
    // use in scheduler
    public boolean sendToClient(String sessionId,BoardTask task) throws IOException {
        boolean send=false;
        WebSocketSession session = sessions.get(sessionId);
        if (session != null && session.isOpen()) {
            String message=objectToJson(task);
            session.sendMessage(new TextMessage(message));
            send=true;
        }else if(session != null && !session.isOpen()){
            sessions.remove(session.getId());
        }
        return send;
    }
    
}
