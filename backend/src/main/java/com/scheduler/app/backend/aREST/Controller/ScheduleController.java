package com.scheduler.app.backend.aREST.Controller;

import static org.mockito.Answers.values;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.app.backend.aREST.Models.Schedule;
import com.scheduler.app.backend.aREST.Service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService service;
    @PostMapping(value="/schedule/add-http-Schedule")
    public Schedule addHttpScheduleTest(@RequestBody Map<String, Object> payload){
        String name=(String) payload.get("name");
        String time=(String) payload.get("time");
        String url=(String) payload.get("url");
        boolean startup=(boolean) payload.get("startup");
        boolean repeat=(boolean) payload.get("repeat");
        return service.addHttpTask(name,time, repeat,startup,url);
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
    @PostMapping("/schedule/test-task")
    public boolean testTask(@RequestBody Map<String, Object> entity) {
        boolean success=false;
        long id=(int) entity.get("id");
        success=service.testTask(id);
        return success;
    }

    
    
}
