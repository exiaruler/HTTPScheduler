package com.scheduler.app.backend.Service;

import org.springframework.stereotype.Service;
import com.scheduler.app.backend.Base;
import com.scheduler.app.backend.Models.Routes;
import com.scheduler.app.backend.Repo.RoutesRepo;
@Service
public class RoutesService extends Base {
    private final RoutesRepo service;

    public RoutesService(RoutesRepo service) {
        this.service = service;
    }
    public Routes addRoute(Routes entry){
        return service.save(entry);
    }
    public void addRoutesByScan(long deviceId,String ip){
       String queryDataRequest=httpUtil.requestRoute(ip, "QueryData","");
       String rawJson=getrawVariable(queryDataRequest);
       String queryData=getDataByField("QueryData",rawJson);
        //httpUtil.httpRequest(null)
    }
}
