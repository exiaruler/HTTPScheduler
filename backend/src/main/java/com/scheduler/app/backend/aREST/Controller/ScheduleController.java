package com.scheduler.app.backend.aREST.Controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
import com.scheduler.Base.MapCast.MapCast;
import com.scheduler.app.backend.aREST.Models.Schedule;
import com.scheduler.app.backend.aREST.Models.FormModel.ScheduleForm;
import com.scheduler.app.backend.aREST.Service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController extends ControllerBase {
    @Autowired
    private ScheduleService service;
   
    @GetMapping("/new-record")
    public ScheduleForm getNewRecord() {
        ScheduleForm test=new ScheduleForm();
        return test;
    }
    
    @PostMapping(value="/add-http-schedule")
    public Schedule addHttpScheduleTest(@RequestBody Map<String, Object> payload){
        MapCast cast=mapCast.mapJson(payload);
        return service.addSchedule(cast.getKeyString("name"),cast.getKeyString("time"),cast.getKeyBoolean("repeat"),cast.getKeyBoolean("startup"),cast.getKeyString("url"),cast.getKeyInteger("device"),cast.getKeyInteger("route"),cast.getKeyInteger("mode"));
    }
    @GetMapping("/get-schedules")
    public List<Schedule> getAllSchedule() {
        return service.getAllSchedule();
    }
    @PostMapping("/startup")
    public boolean startup(@RequestBody Map<String, Object> entity) {
        boolean success=false;
        long id=(int) entity.get("id");
        success=service.startupTask(id);
        return success;
    }
    // boot route from device
    @PostMapping("/device-startup")
    public boolean startupDevice(@RequestBody Map<String, Object> entity) {
        boolean success=false;
        String id=(String) entity.get("id");
        int intId=Integer.valueOf(id);
        success=service.startupTask(intId);
        return success;
    }
    @PostMapping("/test-task")
    public boolean testTask(@RequestBody Map<String, Object> entity) {
        boolean success=false;
        long id=(int) entity.get("id");
        success=service.testTask(id);
        return success;
    }

    
    
}
