package com.scheduler.app.backend.Service;

import org.springframework.stereotype.Service;

import com.scheduler.app.HTTPHandle.HttpUtil;
import com.scheduler.app.backend.Base;
import com.scheduler.app.backend.Models.Board;
import com.scheduler.app.backend.Models.Devices;
import com.scheduler.app.backend.Repo.DeviceRepo;

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
                routesService.addRoutesByScan(save.getId(),ip);
                
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
                routesService.addRoutesByScan(save.getId(), ip);
        }
    }
    


    }

