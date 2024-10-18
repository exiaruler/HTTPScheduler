package com.scheduler.Base;

import org.springframework.web.bind.annotation.GetMapping;

import com.scheduler.Base.MapCast.MapCast;
import com.scheduler.app.backend.aREST.Models.FormModel.ScheduleForm;

public class ControllerBase extends Base {
    public MapCast mapCast=new MapCast();
    public String objectClass="";
    @GetMapping("/new-record")
    public Object getNewRecord() {
        try {
            Class <?> className=Class.forName(objectClass);
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ScheduleForm test=new ScheduleForm();
        return test;
    }
    

}
