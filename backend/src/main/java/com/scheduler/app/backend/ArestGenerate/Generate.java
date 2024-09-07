package com.scheduler.app.backend.ArestGenerate;

import java.util.ArrayList;
import java.util.List;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.aREST.Models.Device;
import com.scheduler.app.backend.aREST.Models.Mode;
import com.scheduler.app.backend.aREST.Models.Route;
// generate code and methods for aRestv2
public class Generate extends Base {
    public String createDeviceData(List <Device> list){
        String device="{";
        
        device=device+"}";
        return device;
    }
    private String createRouteArray(List<Route> routeList){
        List<String> routes=new ArrayList<String>();
        
        for(int i=0; i<routeList.size(); i++){
            Route rou=routeList.get(i);
            List<String> route=new ArrayList<String>();
            List<String> modes=new ArrayList<String>();
            // assign route and if it has multiple modes
            route.add("{"+stringVariable(rou.getRoute()));
            String mode=booleanToString(rou.getModes());
            if(!rou.getModes()){
                 mode=mode+"}";
            }else{
                for(int x=0; x<rou.getMode().size(); x++){
                    Mode modeObj=rou.getMode().get(x);
                    modes.add(stringVariable(modeObj.getMode()));
                }
            }
            route.add(mode);
            if(rou.getModes()){
                String finMode=returnDataString(modes)+"}";
                route.add(finMode);
            }
        }
        String finSting=returnDataString(routes);
        return finSting;
    }
}
