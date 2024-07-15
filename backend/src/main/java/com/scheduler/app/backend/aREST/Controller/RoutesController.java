package com.scheduler.app.backend.aREST.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.app.backend.aREST.Models.*;
import com.scheduler.app.backend.aREST.Service.RoutesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RoutesController {
    @Autowired
    private RoutesService service;

    @GetMapping(value="/routes/get-all-routes")
    public List<Route> getAllRoutes(){
        return service.getAllRoutes();
    }
    @PostMapping("/routes/add-route")
    public Route postMethodName(@RequestBody Map<String, Object> payload) {
        int deviceId=(int) payload.get("deviceid");
        String route=(String) payload.get("route");
        boolean modes=(boolean)payload.get("modes");
        @SuppressWarnings("unchecked")
        List<Mode>list=(List<Mode>) payload.get("mode");
        
        return service.addRoute(deviceId,route,modes,list);
    }
    
    
}
