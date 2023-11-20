package com.scheduler.app.backend.aREST.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.Base.JsonObject.JsonObject;
import com.scheduler.app.backend.aREST.Models.Devices;
import com.scheduler.app.backend.aREST.Models.Mode;
import com.scheduler.app.backend.aREST.Models.Routes;
import com.scheduler.app.backend.aREST.Models.ScanDevice;
import com.scheduler.app.backend.aREST.Repo.ModeRepo;
import com.scheduler.app.backend.aREST.Repo.RoutesRepo;
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
    public Set<Routes> addRoutesByScan(Devices deviceId,String ip,ScanDevice version){
        Set<Routes> routeList=new HashSet<Routes>();
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
        Set <Mode> modeList=new HashSet<Mode>();
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
            String [] arrParams=param.split("\\"+paramControl);
            for(int x=0; x<arrParams.length; x++){
                Mode newMode=new Mode();
                newMode.setRoutes(save);
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
    public List<Routes> getAllRoutes(){
        return service.findAll();
    }
    public Routes getRoute(long id){
        return service.findById(id).get();
    }

    
}
