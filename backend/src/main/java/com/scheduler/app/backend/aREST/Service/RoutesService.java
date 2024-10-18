package com.scheduler.app.backend.aREST.Service;

import java.util.*;
import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.Base.JsonObject.JsonObject;
import com.scheduler.app.backend.Command.Models.Command;
import com.scheduler.app.backend.Command.Service.CommandService;
import com.scheduler.app.backend.aREST.ArestV2Frame;
import com.scheduler.app.backend.aREST.Models.Device;
import com.scheduler.app.backend.aREST.Models.Mode;
import com.scheduler.app.backend.aREST.Models.Route;
import com.scheduler.app.backend.aREST.Models.ScanDevice;
import com.scheduler.app.backend.aREST.Repo.DeviceRepo;
import com.scheduler.app.backend.aREST.Repo.ModeRepo;
import com.scheduler.app.backend.aREST.Repo.RoutesRepo;
// save routes and modes
@Service
public class RoutesService extends Base {
    private final RoutesRepo service;
    private final ModeRepo modeService;
    private final DeviceRepo deviceRepo;
    private final CommandService commandService;
    private final ParameterService parameterService;
    public ArestV2Frame arest=new ArestV2Frame();
    public RoutesService(RoutesRepo service, ModeRepo modeService, DeviceRepo deviceRepo, CommandService commandService, ParameterService parameterService) {
        this.service = service;
        this.modeService = modeService;
        this.deviceRepo = deviceRepo;
        this.commandService = commandService;
        this.parameterService = parameterService;
    }
    
    public Route addRoute(long deviceId,String route,boolean modes,List<Mode>modeList){
        Route newRoute=new Route();
        Device device=deviceRepo.getReferenceById(deviceId);
        if(device!=null){
            newRoute.setDevice(device);
            if(!modes){
                newRoute.setMode(null);
            }else newRoute.setMode(modeList);
        }
        service.save(newRoute);
        return newRoute;
    }
    public Route addRouteCommand(long deviceId,String route,long commandId){
        Route newRoute=new Route();
        newRoute.setRoute(route);
        Device device=deviceRepo.getReferenceById(deviceId);
        Command command=commandService.getCommand(commandId);
        if(device!=null&&command!=null){
            if(command.getParams()) newRoute.setModes(true);
            newRoute.setDevice(device);
            newRoute.setCommand(command);
        }
        service.save(newRoute);
        return newRoute;
    }
    public Mode addMode(long routeId,Mode entry){
        return modeService.save(entry);
    }
    public Mode addModeCommand(long deviceId,String modeName,long routeId,List<String> params){
        Mode newMode=new Mode();
        List<Mode> modeList=new ArrayList<Mode>();
        Device device=deviceRepo.getReferenceById(deviceId);
        Route route=service.getReferenceById(routeId);
        newMode.setMode(modeName);
        if(device!=null&&route!=null){
            Command command=route.getCommand();
            if(params.size()==command.getTotalParam()){
                // create mode
                newMode.setParams(parameterService.addParameters(params, newMode));
                newMode.setRoute(route);
                //Mode save=modeService.save(newMode);
                modeList.add(newMode);
                route.setMode(modeList);
            }
        }
        service.save(route);
        Mode savedMode=route.getMode().get(route.getMode().size()-1);
        return savedMode;
    }
    public Route updateRoute(Route entry,long id){
        Route rec=null;
        if(service.existsById(id)){
            rec=service.findById(id).get();
            rec=entry;
            service.save(rec);
        }
        return rec;
    }
    public List<Route> addRoutesByScan(Device deviceId,String ip){
        List<Route> routeList=new ArrayList<Route>();
        if(arest.testRoutes(ip)){
            String rawJson=httpUtil.request(ip);
            String queryDataRequest="QueryData";
            JsonObject json=jsonobj.jsonToObject(rawJson);
            String queryData=json.findKeyValue(queryDataRequest);
            String [] rawRoute=queryData.split("\\|\\|");
            // controller of version of routes and params
            String [] controlArr=rawRoute[0].trim().split("");
            String routeControl=controlArr[0];
            String paramControl=controlArr[1]; 
            String [] arr=rawRoute[1].split("\\"+routeControl);

            // loop and save routes and modes 
            for(int i=0; i<arr.length; i++){
                List <Mode> modeList=new ArrayList<Mode>();
                String route=arr[i];
                Route newRoute=new Route();
                String[] routeParam=getRoute(route);
                Route exist=service.findExistingRouteByDevice(routeParam[0],deviceId.getId());
                if(exist!=null){
                    newRoute=exist;
                }else{
                newRoute.setDevice(deviceId);
                }
                String param="";
                // if there params 
                if(routeParam[1]!=""){
                    param=routeParam[1];
                    String routeItself=routeParam[0];
                    //save param of that route
                    if(param!=""){
                        String [] arrParams=param.split("\\"+paramControl);
                        for(int x=0; x<arrParams.length; x++){
                            Mode newMode=new Mode();
                            Mode existMode=modeService.findMode(arrParams[x],newRoute.getId());
                            if(existMode!=null) newMode=existMode;
                            newMode.setRoute(newRoute);
                            newMode.setMode(arrParams[x]);
                            modeList.add(newMode);
                        }
                    }
                    newRoute.setRoute(routeItself);
                    newRoute.setModes(true);
                    newRoute.setMode(modeList);
                }else{
                    newRoute.setRoute(route);
                }
                routeList.add(newRoute);
            }
        }
        return routeList;
    }
    private String[] getRoute(String fullRoute){
        String routeParam[]={"",""};
        int bracketStartI=fullRoute.indexOf("(");
        int bracketEndI=fullRoute.indexOf(")");
        if(bracketEndI>-1&&bracketEndI>-1){
            String param=fullRoute.substring(bracketStartI+1, bracketEndI);
            String route=fullRoute.substring(0,bracketStartI);
            routeParam[0]=route;
            routeParam[1]=param;
        }else{
            routeParam[0]=fullRoute;
        }
        return routeParam;
    }
    // routes
    public List<Route> getAllRoutes(){
        return service.findAll();
    }
    public Route getRoute(long id){
        return service.findById(id).get();
    }
    // modes
    public List<Mode> getAllModes(){
        return modeService.findAll();
    }
    public Mode getMode(long id){
        return modeService.findById(id).get();
    }
    

    
}
