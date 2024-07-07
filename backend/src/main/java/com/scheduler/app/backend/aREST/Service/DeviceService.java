package com.scheduler.app.backend.aREST.Service;
import java.util.*;

import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.Base.JsonObject.JsonObject;
import com.scheduler.app.backend.Task.Model.CompletedTask;
import com.scheduler.app.backend.aREST.ArestV2Frame;
import com.scheduler.app.backend.aREST.Models.Board;
import com.scheduler.app.backend.aREST.Models.Device;
import com.scheduler.app.backend.aREST.Models.Route;
import com.scheduler.app.backend.aREST.Models.ScanDevice;
import com.scheduler.app.backend.aREST.Repo.DeviceRepo;

@Service
public class DeviceService extends Base {
    private final DeviceRepo device;
    private final RoutesService routesService;
    public ArestV2Frame arest=new ArestV2Frame();

    public DeviceService(DeviceRepo device, RoutesService routesService) {
        this.device = device;
        this.routesService = routesService;
    }
    
    public Device addDevice(Device entry){
        return device.save(entry);
    }
    public Device updateDevice(Device obj,long id){
        Device rec=device.findById(id).get();
        Device save=null;
        if(rec!=null){
            rec.setDeviceName(obj.getDeviceName());

            save=device.save(rec);
        }
        return save;
    }
    public List<Device> addDeviceFromScan(Board board,String ip,JsonObject jsonObj){
        List<Device> deviceList=new ArrayList<Device>();
        if(arest.testDeviceFramework(jsonObj, ip)){
            String device=jsonObj.findKeyValue("Devices");
            if(device.indexOf("|")>-1){
                String[] deviceArr=device.split("\\|");
                for(int i=0; i<deviceArr.length; i++){
                    String deviceName=deviceArr[i];
                    //httpUtil.getRoutes(ip,deviceName.trim());
                    Device newDevice=new Device();
                    newDevice.setBoard(board);
                    newDevice.setDeviceName(deviceName);
                    // saves routes
                    List<Route> route=routesService.addRoutesByScan(newDevice,ip);
                    newDevice.setRoutes(route);
                    deviceList.add(newDevice);
                    //Device save=addDevice(newDevice);
                    
                    //save.setRoutes(route);
                    //save=updateDevice(save,save.getId());
                    //device.save(save);
                    //deviceList.add(save);
                }
            }else{
                String deviceName=device.trim();
                try{
                    httpUtil.getRoutes(ip, deviceName);
                }catch(Exception err){

                }
                Device newDevice=new Device();
                newDevice.setBoard(board);
                newDevice.setDeviceName(deviceName);
                Device save=addDevice(newDevice);
                save.setRoutes(routesService.addRoutesByScan(save,ip));
                save=addDevice(save);
                deviceList.add(save);
            }  
        }
        return deviceList;
    }
    // test device framework if it follows arestv2
    public boolean testDeviceFramework(JsonObject obj,String ip){
        String [] keys={"Devices","Background","QueryData","SetDevice","Warning","Status"};
        boolean out=false;
        for(int i=0; i<keys.length; i++){
            if(obj.checkKey(keys[i])){
                out=true;
                // test if there any devices listed and it's string and character '|'
                if(keys[i].equals("Devices")){
                    String value=obj.findKeyValue("Devices");
                    String [] arr=value.split("\\|");
                    if(arr.length>1){
                        
                    }
                    if(value.length()<=0){
                        out=false;
                        break;
                    }
                }
                // test if background key is a boolean
                if(keys[i].equals("Background")){
                    String value=obj.findKeyValue("Background");
                    if(!value.equals("false")||!value.equals("true")){
                        out=false;
                        break;
                    }
                }
                // test for string
                if(keys[i].equals("QueryData")){
                    
                }
                // test for string
                if(keys[i].equals("SetDevice")){
                    
                }
                // test for string
                if(keys[i].equals("Warning")){
                    
                }
                // test for string
                if(keys[i].equals("Status")){
                    
                }
            }
            else
            {
                out=false;
                break;
            }
        }
        // validate query data commands
        String queryDataTest="routes,type,subtype,components,background";
        String query=requestQuery(ip,"1");
        if(!queryDataTest.equals(query)){
             out=false;
        }else
        // test commands
        {
            String arr[]=queryDataTest.split(",");
            for(int i=0; i<arr.length; i++){
                JsonObject json=jsonobj.jsonToObject(httpUtil.requestRoute(ip,"query", arr[i]));
                if(json.checkKey("return_value")){
                    if(json.findKeyValue("return_value").equals("1")){
                        out=true;
                    }else
                    {
                     out=false;
                     break;   
                    }
                }else
                {
                    out=false;
                    break;
                }
            }

        } 
        return out;
    }
    public List<Device> getAllDevice(){
        return device.findAll();
    }
    public Device getDevice(long id){
        return device.findById(id).get();
    }
    public void deleteAllBoard(){
        device.deleteAll();
    }
    // update device after http request
    public void updateDeviceAfterAction(CompletedTask task){
       Device rec=task.getDevice();
       if(rec!=null){
        // update device state and warning
        if(task.getWarning()!=""&&!task.getStatus()){
            rec.setWarning(task.getWarning());
            rec.setState("");
        }else{
            if(task.getStatusString()!=""){
                rec.setState(task.getStatusString());
            }else{
                rec.setState("off");
            }
        }
        device.save(rec);
       }
    }
    


    }

