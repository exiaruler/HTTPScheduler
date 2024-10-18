package com.scheduler.app.backend.aREST.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
import com.scheduler.Base.MapCast.MapCast;
import com.scheduler.app.backend.aREST.Models.*;
import com.scheduler.app.backend.aREST.Service.RoutesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/routes")
public class RoutesController extends ControllerBase {
    @Autowired
    private RoutesService service;

    public RoutesController() {
        objectClass="com.scheduler.app.backend.aREST.Models.Route";
    }

    @GetMapping(value="/get-all-routes")
    public List<Route> getAllRoutes(){
        return service.getAllRoutes();
    }
    @PostMapping("/add-route")
    public Route postMethodName(@RequestBody Map<String, Object> payload) {
        int deviceId=(int) payload.get("deviceid");
        String route=(String) payload.get("route");
        boolean modes=(boolean)payload.get("modes");
        @SuppressWarnings("unchecked")
        List<Mode>list=(List<Mode>) payload.get("mode");
        
        return service.addRoute(deviceId,route,modes,list);
    }
    @PostMapping("/add-route-com/{id}")
    public Route addRouteCommand(@PathVariable long id,@RequestBody Map<String, Object> payload) {
        MapCast cast=mapCast.mapJson(payload); 
        return service.addRouteCommand(id,cast.getKeyString("route"),cast.getKeyInteger("command"));
    }
    @PostMapping("/add-mode-com/{deviceId}/{routeId}")
    public Mode addModeCommand(@PathVariable long deviceId,@PathVariable long routeId,@RequestBody Map<String, Object> payload) {
        MapCast cast=mapCast.mapJson(payload); 
        return service.addModeCommand(deviceId,cast.getKeyString("name"),routeId,cast.getKeyArrayListString("params"));
    }


    
    
}
