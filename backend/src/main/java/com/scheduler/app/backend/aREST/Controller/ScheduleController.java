package com.scheduler.app.backend.aREST.Controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
import com.scheduler.Base.MapCast.MapCast;
import com.scheduler.app.backend.aREST.Models.Schedule;
import com.scheduler.app.backend.aREST.Models.FormModel.ScheduleForm;
import com.scheduler.app.backend.aREST.Service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ScheduleController extends ControllerBase {
    @Autowired
    private ScheduleService service;
   
    @GetMapping("/schedule/new-record")
    public ScheduleForm getNewRecord() {
        ScheduleForm test=new ScheduleForm();
        return test;
    }
    
    @PostMapping(value="/schedule/add-http-Schedule")
    public Schedule addHttpScheduleTest(@RequestBody Map<String, Object> payload){
        String name=(String) payload.get("name");
        String time=(String) payload.get("time");
        String url=(String) payload.get("url");
        boolean startup=(boolean) payload.get("startup");
        boolean repeat=(boolean) payload.get("repeat");
        long deviceId=(int) payload.get("device");
        long routeId=(int) payload.get("route");
        long modeId=(int) payload.get("mode");
        MapCast t=mapCast.mapJson(payload);
        return service.addSchedule(t.getKeyString("name"),t.getKeyString("time"),t.getKeyBoolean("repeat"),t.getKeyBoolean("startup"),t.getKeyString("url"),t.getKeyInteger("device"), t.getKeyInteger("route"),t.getKeyInteger("mode"));
    }
    @PostMapping(value="/schedule/add-http-schedule-form")
    public Schedule addHttpSchedule(@RequestBody ScheduleForm form){
        return service.addScheduleRecord(form.getModel(),form.getCustomComponents().get(0));
    }
    @GetMapping("/schedule/get-schedules")
    public List<Schedule> getAllSchedule() {
        return service.getAllSchedule();
    }
    @PostMapping("/schedule/startup")
    public boolean startup(@RequestBody Map<String, Object> entity) {
        boolean success=false;
        long id=(int) entity.get("id");
        success=service.startupTask(id);
        return success;
    }
    // boot route from device
    @PostMapping("/schedule/device-startup")
    public boolean startupDevice(@RequestBody Map<String, Object> entity) {
        boolean success=false;
        String id=(String) entity.get("id");
        int intId=Integer.valueOf(id);
        success=service.startupTask(intId);
        return success;
    }
    @PostMapping("/schedule/test-task")
    public boolean testTask(@RequestBody Map<String, Object> entity) {
        boolean success=false;
        long id=(int) entity.get("id");
        success=service.testTask(id);
        return success;
    }

    
    
}
