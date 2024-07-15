package com.scheduler.app.backend.aREST.Service;
import java.util.*;

import javax.management.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.Base.JsonObject.JsonObject;
import com.scheduler.app.backend.Task.Model.CompletedTask;
import com.scheduler.app.backend.aREST.ArestV2Frame;
import com.scheduler.app.backend.aREST.Models.Board;
import com.scheduler.app.backend.aREST.Models.Device;
import com.scheduler.app.backend.aREST.Models.Route;
import com.scheduler.app.backend.aREST.Models.ScanDevice;
import com.scheduler.app.backend.aREST.Repo.BoardRepo;
import com.scheduler.app.backend.aREST.Repo.DeviceRepo;

@Service
public class DeviceService extends Base {
    private final DeviceRepo device;
    private final RoutesService routesService;
    public ArestV2Frame arest=new ArestV2Frame();
    private final BoardRepo boardRepo;

    public DeviceService(DeviceRepo device, RoutesService routesService, BoardRepo boardRepo) {
        this.device = device;
        this.routesService = routesService;
        this.boardRepo = boardRepo;
    }
    
    public Device addDevice(Device entry){
        return device.save(entry);
    }
    public Device saveDeviceManual(long board,String name,String type,String subtype,boolean framework,boolean custom){
        Device newDev=new Device();
        Board boardObj=boardRepo.getReferenceById(board);
        if(boardObj!=null){
            List<Device>deviceList=boardObj.getDevice();
            deviceList.add(newDev);
            boardObj.setDevice(deviceList);
            newDev.setBoard(boardObj);
            newDev.setName(name);
            newDev.setType(type);
            newDev.setSubtype(subtype);
            newDev.setFrameworkFollowed(framework);
            newDev.setCustom(custom);
            device.save(newDev);
        }
        return newDev;
    }
    public Device updateDevice(long id,Device deviceObj){
        Device updateDev=device.getReferenceById(id);
        if(updateDev!=null){
            updateDev=deviceObj;
            device.save(updateDev);
        }
        return updateDev;
    }
    public List<Device> addDeviceFromScan(Board board,String ip,JsonObject jsonObj){
        List<Device> deviceList=new ArrayList<Device>();
        if(arest.testDeviceFramework(jsonObj, ip)){
            String device=jsonObj.findKeyValue("Devices");
            if(device.indexOf("|")>-1){
                String[] deviceArr=device.split("\\|");
                for(int i=0; i<deviceArr.length; i++){
                    String deviceName=deviceArr[i];
                    Device newDevice=new Device();
                    newDevice.setFrameworkFollowed(true);
                    newDevice.setBoard(board);
                    newDevice.setName(deviceName);
                    // saves routes
                    List<Route> route=routesService.addRoutesByScan(newDevice,ip);
                    newDevice.setRoutes(route);
                    deviceList.add(newDevice);
                }
            }else{
                String deviceName=device.trim();
                try{
                    httpUtil.getRoutes(ip, deviceName);
                }catch(Exception err){

                }
                Device newDevice=new Device();
                newDevice.setBoard(board);
                newDevice.setName(deviceName);
                Device save=addDevice(newDevice);
                save.setRoutes(routesService.addRoutesByScan(save,ip));
                save=addDevice(save);
                deviceList.add(save);
            }  
        }
        return deviceList;
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
    public void updateDeviceAfterAction(CompletedTask task,Device deviceUpdate){
        
       String state="";
       String warning="";
       if(deviceUpdate!=null){
        // update device state and warning
        if(task.getWarning()!=""&&!task.getStatus()){
            warning=task.getWarning();
        }else{
            if(task.getStatusString()!=""){
                state=task.getStatusString();
            }else{
                state="offline";
            }
        }
        device.updateStateAndWarning(deviceUpdate.getId(),state,warning);
        System.out.println(state);
       }

    }
    
}

