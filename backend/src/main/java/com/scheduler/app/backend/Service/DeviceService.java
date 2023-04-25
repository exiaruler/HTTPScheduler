package com.scheduler.app.backend.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.scheduler.app.backend.Base;
import com.scheduler.app.backend.Models.Board;
import com.scheduler.app.backend.Models.Devices;
import com.scheduler.app.backend.Repo.DeviceRepo;
import com.scheduler.app.backend.Task.Model.CompletedTask;

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
    public void addDeviceFromScan(Board board,String ip,String json){
        String value=getVariableData(json);
        String [] arr=value.split(",");
        String deviceString=getDataByField("Devices",arr[0]);
        if(deviceString.indexOf("|")>-1){
            String[] deviceArr=deviceString.trim().split("\\|");
            for(int i=0; i<deviceArr.length; i++){
                String deviceName=deviceArr[i];
                httpUtil.getRoutes(ip, deviceName);
                Devices newDevice=new Devices();
                newDevice.setBoardId(board);
                newDevice.setDeviceName(deviceName);
                Devices save=addDevice(newDevice);
                // saves routes
                routesService.addRoutesByScan(save,ip);
                
            }
        }else{
                String deviceName=deviceString;
                try{
                    httpUtil.getRoutes(ip, deviceName);
                }catch(Exception err){

                }
                Devices newDevice=new Devices();
                newDevice.setBoardId(board);
                newDevice.setDeviceName(deviceName);
                Devices save=addDevice(newDevice);
                routesService.addRoutesByScan(save, ip);
        }
    }
    public List<Devices> getAllDevice(){
        return device.findAll();
    }
    public Devices getDevice(long id){
        return device.findById(id).get();
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

