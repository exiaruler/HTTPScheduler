package com.scheduler.app.backend.aREST.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.app.backend.aREST.Models.*;
import com.scheduler.app.backend.aREST.Service.RoutesService;
@RestController
public class RoutesController {
    @Autowired
    private RoutesService service;

    @GetMapping(value="/routes/get-all-routes")
    public List<Route> getAllRoutes(){
        return service.getAllRoutes();
    }
    
}
