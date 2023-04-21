package com.scheduler.app.backend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.scheduler.app.backend.Models.Task;
import com.scheduler.app.backend.Service.TaskService;

@RestController
public class TaskController {
    private String url="/task/";
    @Autowired
    private TaskService service;
    @PostMapping(value="/task/addtask")
    public Task addTask(@RequestBody Task entry){
        return service.addTask(entry);
    }
    @GetMapping(value="/task/get-task/{id}")
    public Optional<Task> getTask(@PathVariable long id){
        return service.getTask(id);
    }
    @GetMapping(value="/task/getalltask")
    public List<Task> getAllTask(){
        return service.getAllTask();
    }
    @GetMapping(value="/task/get-all-run-task")
    public List<Task> getAllRunningTask(){
        return service.getAllRunningTask();
    }
    @DeleteMapping(value="/task/delete-task/{id}")
    public void deleteTask(@PathVariable long id){
        service.deleteTask(id);
    }
    @GetMapping(value="/task/start-device/{device}")
    public void addTaskFromRequestStart(@PathVariable String device){

    }
    @GetMapping(value="/task/send-device/{device}")
    public void sendRequestDevice(@PathVariable String device){

    }
}
