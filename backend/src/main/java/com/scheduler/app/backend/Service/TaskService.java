package com.scheduler.app.backend.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.scheduler.app.backend.Models.*;
import com.scheduler.app.backend.Repo.TaskRepo;
import com.scheduler.app.backend.Base;
import com.scheduler.app.backend.Task.SchedulerTask;
// crud on database. does not manipulate scheduler
@Service
public class TaskService extends Base{
    private final TaskRepo service;
    private SchedulerTask sche=new SchedulerTask();

    public TaskService(TaskRepo service) {
        this.service = service;
    }
    public Task addTask(Task entry){
        LocalDateTime setDt=addDuration(0.1);
        entry.setScheduledTime(setDt);
        Task add=service.save(entry);
        addToScheduler();
        return add;
    }
    public void deleteTask(long id){
        service.deleteById(id);
        addToScheduler();   
    }
    public Optional<Task> getTask(long id){
        return service.findById(id);
    }
    public List<Task> getAllTask(){
        return service.findAll();
    }
    public List<Task> getAllRunningTask(){
        List<Task> list=sche.getAllRunTask();
        return list;
    }
    // add task to scheduler
    public void addToScheduler(){
        List <Task> list=getAllTask();
        sche.addToQueue(list);
        
    }
    // schedule time and seconds for task
    private LocalDateTime addDuration(double time){
        LocalDateTime currenDateTime = LocalDateTime.now();
        int mins=(int) time;
        long seconds=(long) (time-mins);
        currenDateTime=currenDateTime.plusMinutes(mins).plusSeconds(seconds);
        return currenDateTime;
    }


}
