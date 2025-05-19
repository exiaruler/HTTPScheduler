package com.scheduler.app.backend.Command;

import java.util.concurrent.TimeUnit;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.Command.Models.Command;

public class CommandFunction extends Base {
    public Command command;
    public String[] commandParameters;


    public CommandFunction() {
    }

    public CommandFunction(Command command, String[] commandParameters) {
        this.command = command;
        this.commandParameters = commandParameters;
    }
    
     // New methods for scheduler use
     
    public boolean setLed(String ip,String electrode,String [] arr){
        boolean success=false;
        if(checkElectrode(electrode)){
            success=setLedAnode(ip, arr);
        }else success=setLedCathode(ip, arr);
        return success;
    }
    
    public boolean setRgb(String ip,String electrode,String [] arr){
        boolean success=false;
        if(checkElectrode(electrode)){
            success=setRgbAnode(ip, arr);
        }else success=setRgbCathode(ip, arr);
        return success;
    }
    public boolean setRgbRandom(String ip,String electrode,String [] arr)
    {
        boolean success=false;
        String params []={arr[0],arr[1],arr[2],randomAnalog(),randomAnalog(),randomAnalog()};
        if(checkElectrode(electrode)){
            success=setRgbAnode(ip,params);
        }else success=setRgbCathode(ip,params);
        return success;
    }

    public boolean setLedBlink(String ip,String [] arr){
        boolean success=false;
        String route=createRouteWithMethod(ip,"led-animation", "ledBlinkBackGround".toLowerCase())+paramArrayToString(arr);
        success=successCheck(httpUtil.requestDevice(route,5,""));
        return success;
    }
    public boolean setLedFade(String ip,String [] arr){
        boolean success=false;
        String route=createRouteWithMethod(ip,"led-animation", "ledFade".toLowerCase())+paramArrayToString(arr);
        success=successCheck(httpUtil.requestDevice(route,5,""));
        return success;
    }
    public boolean ledBrightnessBuildUp(String ip,String electrode,String [] arr){
        boolean success=false;
        if(checkElectrode(electrode)){
            success=ledAnodeBrightnessBuildUp(ip, arr);
        }else success=ledCathodeBrightnessBuildUp(ip, arr);
        return success;
    }
    public boolean setLedCathode(String ip,String [] arr){
        boolean success=false;
        String param=paramArrayToString(arr);
        String route=httpUtil.createRoute(ip,"set-led", param);
        success=successCheck(httpUtil.requestDevice(route,5,""));
        return success;
    }
    public boolean setLedAnode(String ip,String [] arr){
        boolean success=false;
        arr[1]=calculateOutputAnode(arr[1]);
        String param=paramArrayToString(arr);
        String route=httpUtil.createRoute(ip,"set-led", param);
        success=successCheck(httpUtil.requestDevice(route,5,""));
        return success;
    }
    public boolean ledCathodeBrightnessBuildUp(String ip,String [] arr){
        boolean success=false;
        String pin=arr[0];
        int start=Integer.parseInt(arr[1]);
        int target=Integer.parseInt(arr[2]);
        int delay=Integer.parseInt(arr[3]);
        int add=Integer.parseInt(arr[4]);
        int count=0;
        // set start brightness
        String Startparam[]={pin,Integer.toString(start)};
        setLedCathode(ip,Startparam);
        if(start<target){
            while(count<=target){
                try {
                    String analogWrite=Integer.toString(count);
                    String param[]={pin,analogWrite};
                    success=setLedCathode(ip, param);
                    miliSecondDelay(delay);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                count=count+add;
            }
        }else if(start>target){
            count=start;
            while(target<=count){
                try {
                    String analogWrite=Integer.toString(count);
                    String param[]={pin,analogWrite};
                    success=setLedCathode(ip, param);
                    miliSecondDelay(delay);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                count=count-add;
            }
        }
        return success;
    }
    public boolean ledAnodeBrightnessBuildUp(String ip,String [] arr){
        boolean success=false;
        String pin=arr[0];
        int start=Integer.parseInt(arr[1]);
        int target=Integer.parseInt(arr[2]);
        int delay=Integer.parseInt(arr[3]);
        int add=Integer.parseInt(arr[4]);
        int count=0;
        // set start brightness
        String Startparam[]={pin,Integer.toString(start)};
        setLedAnode(ip,Startparam);
        if(start<target){
            while(count<=target){
                try {
                    String analogWrite=Integer.toString(count);
                    String param[]={pin,analogWrite};
                    success=setLedAnode(ip, param);
                    miliSecondDelay(delay);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                count=count+add;
            }
        }else if(start>target){
            count=start;
            while(target<=count){
                try {
                    String analogWrite=Integer.toString(count);
                    String param[]={pin,analogWrite};
                    success=setLedAnode(ip, param);
                    miliSecondDelay(delay);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                count=count-add;
            }
        }
        return success;
    }
    
    public boolean setRgbFadeAnimation(String ip,String [] arr){
        boolean success=false;
        return success;
    }
    
    public boolean setRgbAnode(String ip,String [] arr){
        boolean success=false;
        String route=createRouteWithMethod(ip,"set-rgb", "COMMON_ANODE")+paramArrayToString(arr);
        success=successCheck(httpUtil.requestDevice(route,5,""));
        return success;
    }
    public boolean setRgbCathode(String ip,String [] arr){
        boolean success=false;
        String route=createRouteWithMethod(ip,"set-rgb", "COMMON_CATHODE")+paramArrayToString(arr);
        success=successCheck(httpUtil.requestDevice(route,5,""));
        return success;
    }
    public boolean setRgbRandomAnode(String ip,String [] arr)
    {
        boolean success=false;
        String params []={arr[0],arr[1],arr[2],randomAnalog(),randomAnalog(),randomAnalog()};
        success=setRgbAnode(ip,params);
        return success;
    }
    public boolean setRgbRandomCathode(String ip,String [] arr)
    {
        boolean success=false;
        String params []={arr[0],arr[1],arr[2],randomAnalog(),randomAnalog(),randomAnalog()};
        success=setRgbCathode(ip,params);
        return success;
    }
    public boolean setRgbRandomCathodeSwitch(String ip,String [] arr){
        boolean success=false;
        String [] key={ip,arr[0],arr[1],arr[2]};
        int red=0,green=0,blue=0;
        if(memory.containsKey(key)){
            String value=memory.get(key);
            String rgbArray[]=value.split(",");
            red=Integer.parseInt(rgbArray[0]);
            green=red=Integer.parseInt(rgbArray[1]);
            blue=Integer.parseInt(rgbArray[2]);
        }else{
            String params []={arr[0],arr[1],arr[2],randomAnalog(),randomAnalog(),randomAnalog()};
            String convertToString=String.join(",",params);
            success=setRgbCathode(ip,params);
            memory.put(key, convertToString);
        }
        
        return success;
    }
    public boolean clearQueue(String ip){
        boolean success=false;
        String route=httpUtil.createRoute(ip,"clear-queue","clear");
        success=successCheck(httpUtil.requestDevice(route,5,""));
        return success;
    }
    public boolean clearQueueDevice(String ip,String [] arr){
        boolean success=false;
        String route=createRouteWithMethod(ip,"clear-queue", "removeDeviceTask".toLowerCase())+paramArrayToString(arr);
        success=successCheck(httpUtil.requestDevice(route,5,""));
        return success;
    }
    public boolean setServo(String ip,String [] arr){
        boolean success=false;
        String param=paramArrayToString(arr);
        String route=httpUtil.createRoute(ip,"set-servo", param);
        success=successCheck(httpUtil.requestDevice(route,5,""));
        return success;
    }
    public boolean setServoSynchronous(String ip,String [] arr){
        boolean success=false;
        // int start angle
        int start=Integer.parseInt(arr[1]);
        // int move angle
        int move=Integer.parseInt(arr[2]);
        // int gap
        int gap=Integer.parseInt(arr[3]);
        // int delay
        int delay=Integer.parseInt(arr[4]);
        // int loop
        int repeat=Integer.parseInt(arr[5]);
        int previousPos=0;
        String previousPosString="";
        // start
        try {
            String startDegree=Integer.toString(start);
            String startArr[]={arr[0],startDegree,startDegree,"0","0","1"};
            success=setServo(ip, startArr);
            miliSecondDelay(gap);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        previousPos=start;
        // loop and move
        for(int a=0; a<repeat; a++){
            try {
                // move towards that angle
                for(int i=start; i<move; i+=5){
                    String moveDegree=Integer.toString(i);
                    previousPosString=Integer.toString(previousPos);
                    String moveArr[]={arr[0],moveDegree,moveDegree,"0","0","1"};
                    success=setServo(ip, moveArr);
                    miliSecondDelay(15);
                    previousPos=i;
                }
                miliSecondDelay(delay);
                // go back
                for(int c=move; c>=start; c-=5){
                    String backDegree=Integer.toString(c);
                    previousPosString=Integer.toString(previousPos);
                    String backArr[]={arr[0],backDegree,backDegree,"0","0","1"};
                    success=setServo(ip, backArr);
                    miliSecondDelay(15);
                    previousPos=c;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return success;
    }
    // common logic
    public String calculateOutputAnode(String value){
        int intValue=Integer.parseInt(value);
        int sum=255-intValue;
        return String.valueOf(sum);
    }
    public boolean successCheck(String response){
        boolean result=false;
        String rawVariable=getrawVariable(response);
        String returnValue=getDataByFieldRevamp("return_value",rawVariable);
        if(returnValue.equals("1")){
            result=true;
        }
        return result;
    }
    public String createRouteWithMethod(String ip,String route,String command){
        String url=httpUtil.createRoute(ip, route, command)+"&";
        return url;
    }
    public String paramArrayToString(String array []){
        return String.join("&",array);
    }
    public void miliSecondDelay(long time) throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(time);
    }
    private String randomAnalog(){
        String random=Integer.toString(getRandomNumber(0, 256));
        return random;
    }
    // check electrode type if it's anode return true
    public boolean checkElectrode(String electrode){
        boolean anode=false;
        if(electrode.equals("anode")) anode=true;
        return anode;
    }
    
}
