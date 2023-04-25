package com.scheduler.app.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.app.backend.Service.RoutesService;
import com.scheduler.app.backend.Models.*;
@RestController
public class RoutesController {
    @Autowired
    private RoutesService service;

    @GetMapping(value="/routes/get-all-routes")
    public List<Routes> getAllRoutes(){
        return service.getAllRoutes();
    }
    
}
