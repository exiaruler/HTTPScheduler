package com.scheduler.app.backend.Task.Thread;

import java.io.IOException;

import com.scheduler.Base.ThreadBase.BaseThread;
import com.scheduler.app.backend.Messaging.Board.WebSocketHandlerRaw;
import com.scheduler.app.backend.Messaging.Models.BoardTask;
import com.scheduler.app.backend.aREST.Models.Device;
import com.scheduler.app.backend.aREST.Models.Mode;
import com.scheduler.app.backend.aREST.Models.Route;
import com.scheduler.app.backend.aREST.Models.Task;

public class SocketSchedule extends BaseThread{

    private Task task;
    private Device device;
    private Route route;
    private Mode mode;
    private String[] params;
    private WebSocketHandlerRaw webSocketHandlerRaw;
    public SocketSchedule(Task task,Device device,Route route,Mode mode,String[] params) {
        this.task=task;
        this.device=device;
        this.route=route;
        this.mode=mode;
        this.params=params;
    }
    @Override
    public void run(){
        try {
            sendRequest(task,device,route,mode,params);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    // send request and sends reponse to services to update devices
    public void sendRequest(Task task,Device device,Route route,Mode mode,String[] params) throws InstantiationException{
        long startTime = System.nanoTime();
        
        boolean sucess=false;
        String state="";
        String warning="";
        boolean complete=false;
        if(device!=null){
            String deviceIp=device.getBoard().getIp();
            if(device.getBoard().getSocket()){
                String wsId=device.getBoard().getWebsocketId();
                BoardTask commandInput=null; 
                String modeState=mode.getMode();
                String classString=route.getCommand().getClassName();
                String methodName=route.getCommand().getCommand();
                if(route.getModes()){
                    commandInput=mode.getBoardAction();
                }else if(!route.getModes()){
                    commandInput=route.getBoardAction();
                }
                if(commandInput!=null){
                    try {
                        boolean sent=webSocketHandlerRaw.sendToClient(wsId, commandInput);
                        if(sent){
                            sucess=true;    
                            state=modeState;
                            complete=true;
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                long endTime = System.nanoTime();
                sche.addToComplete(device, sucess, state, warning,complete,task);
            }else sche.failedTask(task,device);
        }else
        {
            sche.failedTask(task,device);
        }
    }
}
