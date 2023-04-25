package com.scheduler.app.backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.scheduler.app.backend.Base;
import com.scheduler.app.backend.Models.Devices;
import com.scheduler.app.backend.Models.Mode;
import com.scheduler.app.backend.Models.Routes;
import com.scheduler.app.backend.Repo.ModeRepo;
import com.scheduler.app.backend.Repo.RoutesRepo;
// save routes and modes
@Service
public class RoutesService extends Base {
    private final RoutesRepo service;
    private final ModeRepo modeService;

    public RoutesService(RoutesRepo service, ModeRepo modeService) {
        this.service = service;
        this.modeService = modeService;
    }
    
    public Routes addRoute(Routes entry){
        return service.save(entry);
    }
    public Mode addMode(Mode entry){
        return modeService.save(entry);
    }
    public Routes updateRoute(Routes entry,long id){
        Routes rec=null;
        if(service.existsById(id)){
            rec=service.findById(id).get();
            rec=entry;
            service.save(rec);
        }
        return rec;
    }
    public void addRoutesByScan(Devices deviceId,String ip){
       String queryDataRequest=httpUtil.requestRoute(ip, "QueryData","");
       String rawJson=getrawVariable(queryDataRequest);
       String queryData=getDataByFieldRevamp("QueryData",rawJson);
       String [] arr=queryData.split("\\|");
       // loop and save routes and modes 
       for(int i=0; i<arr.length; i++){
        String route=arr[i];
        Routes newRoute=new Routes();
        newRoute.setDevice(deviceId);
        int bracketStartI=route.indexOf("(");
        int bracketEndI=route.indexOf(")");
        String param="";
        // if there params 
        if(bracketEndI>-1&&bracketEndI>-1){
            param=route.substring(bracketStartI+1, bracketEndI);
            String routeItself=route.substring(0,bracketStartI);
            newRoute.setRoute(routeItself);
            newRoute.setModes(true);
        }else{
            newRoute.setRoute(route);
        }
        Routes save=addRoute(newRoute);
        //save param of that route
        if(param!=""){
            String [] arrParams=param.split("~");
            for(int x=0; x<arrParams.length; x++){
                Mode newMode=new Mode();
                newMode.setRoutes(save);
                newMode.setMode(arrParams[x]);
                addMode(newMode);
            }
        }
       }
        
    }
    public List<Routes> getAllRoutes(){
        return service.findAll();
    }
    public Routes getRoute(long id){
        return service.findById(id).get();
    }

    
}
