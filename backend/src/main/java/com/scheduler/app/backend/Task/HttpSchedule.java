package com.scheduler.app.backend.Task;
import com.scheduler.app.HTTPHandle.HttpUtil;
import com.scheduler.app.backend.Base;
import com.scheduler.app.backend.Models.*;


// sends action http request to device
public class HttpSchedule extends Thread{
    protected Base base=new Base();
    protected HttpUtil http=new HttpUtil();
    protected SchedulerTask sche=new SchedulerTask();
    private Task task;
    private Devices device;
    public HttpSchedule(Task task,Devices device) {
        this.task=task;
        this.device=device;
        
    }
    @Override
    public void run(){
        sendRequest(task,device);
        
    }
    // send request and sends reponse to services to update devices
    public void sendRequest(Task task,Devices device){
        String deviceIp=device.getBoardId().getIp();
        boolean sucess=false;
        String state="";
        String warning="";
        if(http.requestRouteTest(deviceIp)){
            String response=http.requestDevice(task.getUrl());
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
            }
        }
        sche.removeRunningTask(task);
        // add to complete task queue to update device
        sche.addToComplete(device, sucess, state, warning);
        
    }
}
