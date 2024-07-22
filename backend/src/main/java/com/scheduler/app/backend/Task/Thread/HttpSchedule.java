package com.scheduler.app.backend.Task.Thread;
import org.springframework.stereotype.Service;

import com.scheduler.Base.JsonObject.JsonObject;
import com.scheduler.Base.ThreadBase.BaseThread;
import com.scheduler.app.backend.aREST.Models.*;

// sends action http request to device
public class HttpSchedule extends BaseThread{
    private Task task;
    private Device device;
    public HttpSchedule(Task task,Device device) {
        this.task=task;
        this.device=device;
        
    }
    @Override
    public void run(){
        sendRequest(task,device);
        
    }
    // send request and sends reponse to services to update devices
    public void sendRequest(Task task,Device device){
        long startTime = System.nanoTime();
        boolean sucess=false;
        String state="";
        String warning="";
        boolean complete=false;
        if(device!=null){
            String deviceIp=device.getBoard().getIp();
            if(http.requestRouteTest(deviceIp)){
                JsonObject change=arest.changeDevice(deviceIp,device.getName());
                if(change!=null&&change.findKeyValue("return_value").equals("1")){
                    String response=http.requestDevice(task.getUrl());
                    // get data required to update device in database
                    if(response!=""){
                        String rawVariable=base.getrawVariable(response);
                        String returnValue=base.getDataByFieldRevamp("return_value",rawVariable);
                        JsonObject deviceJson=new JsonObject();
                        String deviceName="";
                        // 1 is sucess
                        if(returnValue.equals("1")){
                            deviceJson=base.jsonobj.jsonToObject(http.request(deviceIp));
                            deviceName=deviceJson.findKeyValue("SetDevice");
                            sucess=true;
                        }
                        // get state of device
                        if(sucess&&deviceName!=""){
                            state=deviceJson.findKeyValue("Status");
                            device.setState(state);
                            System.out.println(state);
                        }else{
                            warning=deviceJson.findKeyValue("Warning");
                            device.setWarning(warning);
                        }
                        complete=true;
                        long endTime = System.nanoTime();
                        sche.removeRunningTask(task);
                        // add to complete task queue to update device
                        sche.addToComplete(device, sucess, state, warning,complete,task);
                    }else sche.failedTask(task,device);
                }sche.failedTask(task,device);
            }else sche.failedTask(task,device);
        }else
        // send http request for non devices
        {
            String response=http.requestDevice(task.getUrl());
            long endTime = System.nanoTime();
            sche.addToComplete(null, sucess, state, warning,true,task);
            
        }
    }
}
