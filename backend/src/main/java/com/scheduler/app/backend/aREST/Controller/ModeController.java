package com.scheduler.app.backend.aREST.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
@RestController
@RequestMapping(value = "/mode")
public class ModeController extends ControllerBase{

    public ModeController(){
        objectClass="com.scheduler.app.backend.aREST.Models.Mode";
    }
}
