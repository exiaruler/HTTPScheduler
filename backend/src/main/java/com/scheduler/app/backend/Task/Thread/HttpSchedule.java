package com.scheduler.app.backend.Task.Thread;
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
        String deviceIp=device.getBoard().getIp();
        boolean sucess=false;
        String state="";
        String warning="";
        boolean complete=false;
        if(http.requestRouteTest(deviceIp)){
            String response=http.requestDevice(task.getUrl());
            // get data to update device status if update enabled
            if(task.getUpdate()){
                // get data required to update device in database
                if(response!=""){
                    String rawVariable=base.getrawVariable(response);
                    String returnValue=base.getDataByFieldRevamp("return_value",rawVariable);
                    // 1 is sucess
                    if(returnValue.equals("1")){
                        sucess=true;
                    }
                    // get state of device
                    if(sucess){
                        String responseState=http.requestRoute(deviceIp,device.getDeviceName()+"State","");
                        String rawState=base.getrawVariable(responseState);
                        state=base.getDataByFieldRevamp(device.getDeviceName()+"State",rawState);
                        System.out.println(state);
                    }else{
                        String responseState=http.requestRoute(deviceIp,device.getDeviceName()+"Warning","");
                        String rawState=base.getrawVariable(responseState);
                        warning=base.getDataByFieldRevamp(device.getDeviceName()+"Warning",rawState);
                    }
                    complete=true;
                    sche.removeRunningTask(task);
                    // add to complete task queue to update device
                    sche.addToComplete(device, sucess, state, warning,complete);
                }else sche.failedTask(task);
            }
            long endTime = System.nanoTime();
        }else sche.failedTask(task);
        
        
    }
}
