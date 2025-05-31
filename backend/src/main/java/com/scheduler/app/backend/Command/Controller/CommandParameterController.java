package com.scheduler.app.backend.Command.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
import com.scheduler.app.backend.Command.Service.CommandParameterService;

@RestController
@RequestMapping(value = "/command-parameter")
public class CommandParameterController extends ControllerBase {
    @Autowired
    private CommandParameterService comParaService;
    public CommandParameterController(){
        this.objectClass=this.pathBase+".Command.Models.CommandParameter";
    }
    @GetMapping(value="/get-electodes")
    public String [] getElectodes(){
        return comParaService.electode;
    }
    
    
}   
