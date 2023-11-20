package com.scheduler.app.backend.aREST.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.Base.JsonObject.JsonObject;
import com.scheduler.app.backend.Task.Model.CompletedTask;
import com.scheduler.app.backend.aREST.Models.Board;
import com.scheduler.app.backend.aREST.Models.Devices;
import com.scheduler.app.backend.aREST.Models.Routes;
import com.scheduler.app.backend.aREST.Models.ScanDevice;
import com.scheduler.app.backend.aREST.Repo.DeviceRepo;

@Service
public class DeviceService extends Base {
    private final DeviceRepo device;
    private final RoutesService routesService;


    public DeviceService(DeviceRepo device, RoutesService routesService) {
        this.device = device;
        this.routesService = routesService;
    }
    
    public Devices addDevice(Devices entry){
        return device.save(entry);
    }
    public Devices updateDevice(Devices obj,long id){
        Devices rec=device.findById(id).get();
        Devices save=null;
        if(rec!=null){
            rec.setDeviceName(obj.getDeviceName());

            save=device.save(rec);
        }
        return save;
    }
    public Set<Devices> addDeviceFromScan(Board board,String ip,JsonObject json,ScanDevice version){
        Set<Devices> deviceList=new HashSet<Devices>();
        String deveice=json.findKeyValue("Devices");
        if(deveice.indexOf("|")>-1){
            String[] deviceArr=deveice.split("\\|");
            for(int i=0; i<deviceArr.length; i++){
                String deviceName=deviceArr[i];
                httpUtil.getRoutes(ip,deviceName.trim());
                Devices newDevice=new Devices();
                newDevice.setBoardId(board);
                newDevice.setDeviceName(deviceName);
                //newDevice.setRoutes(routesService.addRoutesByScan(newDevice,ip,version));
                Devices save=addDevice(newDevice);
                // saves routes
                Set<Routes> route=routesService.addRoutesByScan(save,ip,version);
                save.setRoutes(route);
                //save=updateDevice(save,save.getId());
                device.save(save);
                deviceList.add(save);
            }
        }else{
                String deviceName=deveice.trim();
                try{
                    httpUtil.getRoutes(ip, deviceName);
                }catch(Exception err){

                }
                Devices newDevice=new Devices();
                newDevice.setBoardId(board);
                newDevice.setDeviceName(deviceName);
                Devices save=addDevice(newDevice);
                save.setRoutes(routesService.addRoutesByScan(save,ip,version));
                save=addDevice(save);
                deviceList.add(save);
        }
        return deviceList;
    }
    public List<Devices> getAllDevice(){
        return device.findAll();
    }
    public Devices getDevice(long id){
        return device.findById(id).get();
    }
    public void deleteAllBoard(){
        device.deleteAll();
    }
    // update device after http request
    public void updateDeviceAfterAction(CompletedTask task){
       Devices rec=task.getDevice();
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

