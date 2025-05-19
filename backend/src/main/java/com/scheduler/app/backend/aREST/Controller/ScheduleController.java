package com.scheduler.app.backend.aREST.Controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
import com.scheduler.app.backend.InterfaceModels.Input.ScheduleInput;
import com.scheduler.app.backend.aREST.Models.Schedule;
import com.scheduler.app.backend.aREST.Service.ScheduleService;



@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController extends ControllerBase {
    @Autowired
    private ScheduleService service;

    public ScheduleController() {
        this.objectClass=this.pathBase+".InterfaceModels.Input.ScheduleInput";
    }

    @PostMapping(value="/add-http-schedule")
    public Schedule addHttpScheduleTest(@RequestBody ScheduleInput payload){
        //MapCast cast=mapCast.mapJson(payload);
        return service.addSchedule(payload.getName(),payload.getTime(),payload.getRepeatTask(),payload.getStartup(),payload.getUrl(),payload.getDeviceId(),payload.getRouteId(),payload.getModeId());
    }
    @PostMapping(value="/test")
    public ScheduleInput test(@RequestBody ScheduleInput test){
        return test;
    }
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    @PutMapping("update-schedule/{id}")
    public Schedule updateSchedule(@PathVariable long id, @RequestBody ScheduleInput payload) {
        //TODO: process PUT request
        
        return null;
    }
    @GetMapping("/get-schedules")
    public List<Schedule> getAllSchedule() {
        return service.getAllSchedule();
    }
    @GetMapping("/get-schedule/{id}")
    public Schedule getSchedule(@RequestParam long param) {
        return service.getSchedule(param);
    }
    @DeleteMapping("/delete-schedule")
    public void deleteSchedule(@RequestParam long id){

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
