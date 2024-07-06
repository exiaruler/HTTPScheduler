package com.scheduler.app.backend.aREST.Service;

import java.util.*;
import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.Base.JsonObject.JsonObject;
import com.scheduler.app.backend.aREST.ArestV2Frame;
import com.scheduler.app.backend.aREST.Models.Device;
import com.scheduler.app.backend.aREST.Models.Mode;
import com.scheduler.app.backend.aREST.Models.Route;
import com.scheduler.app.backend.aREST.Models.ScanDevice;
import com.scheduler.app.backend.aREST.Repo.ModeRepo;
import com.scheduler.app.backend.aREST.Repo.RoutesRepo;
// save routes and modes
@Service
public class RoutesService extends Base {
    private final RoutesRepo service;
    private final ModeRepo modeService;
    public ArestV2Frame arest=new ArestV2Frame();
    public RoutesService(RoutesRepo service, ModeRepo modeService) {
        this.service = service;
        this.modeService = modeService;
    }
    
    public Route addRoute(Route entry){
        return service.save(entry);
    }
    public Mode addMode(Mode entry){
        return modeService.save(entry);
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
    public List<Route> addRoutesByScan(Device deviceId,String ip,ScanDevice version){
        List<Route> routeList=new ArrayList<Route>();
       String queryDataRequest=httpUtil.requestRoute(ip,version.getGetRouteData(),"");
       JsonObject json=jsonobj.jsonToObject(queryDataRequest);
       String queryData=json.findKeyValue(version.getGetRouteData()).trim();
       String [] rawRoute=queryData.split("\\|\\|");
       // controller of version of routes and params
       String [] controlArr=rawRoute[0].split("");
       String routeControl=controlArr[0];
       String paramControl=controlArr[1]; 
       String [] arr=rawRoute[1].split("\\"+routeControl);

       // loop and save routes and modes 
       for(int i=0; i<arr.length; i++){
        List <Mode> modeList=new ArrayList<Mode>();
        String route=arr[i];
        Route newRoute=new Route();
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
        Route save=addRoute(newRoute);
        //save param of that route
        if(param!=""){
            String [] arrParams=param.split("\\"+paramControl);
            for(int x=0; x<arrParams.length; x++){
                Mode newMode=new Mode();
                newMode.setRoute(save);
                newMode.setMode(arrParams[x]);
                modeList.add(addMode(newMode));
            }
        }
        save.setMode(modeList);
        save=addRoute(save);
        routeList.add(save);
       }
        return routeList;
    }
    public List<Route> getAllRoutes(){
        return service.findAll();
    }
    public Route getRoute(long id){
        return service.findById(id).get();
    }

    
}
