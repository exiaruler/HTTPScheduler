package com.scheduler.app.backend.aREST.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
import com.scheduler.app.backend.aREST.Models.Component;
import com.scheduler.app.backend.aREST.Models.FormModel.ComponentForm;
import com.scheduler.app.backend.aREST.Service.ComponentService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class ComponentController extends ControllerBase{
    @Autowired
    private ComponentService componentService;

    @GetMapping(value="/component/new-component")
    public ComponentForm newComponent(){
        return new ComponentForm();
    }
    @GetMapping(value="/component/digital-pins")
    public String [] digitalPins(){
        return componentService.getDigitalPins();
    }
    @PostMapping(value = "/component/add-component/{id}",consumes = {"application/xml","application/json"})
    @ResponseBody
    public Component addComponent(@PathVariable long id,@RequestBody Component component){
        System.out.println(id);
        //return componentService.addComponent(id,component);
        return null;
    }
}
