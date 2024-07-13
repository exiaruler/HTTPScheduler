package com.scheduler.app.backend.aREST.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.Task.SchedulerTask;
import com.scheduler.app.backend.aREST.Models.*;
import com.scheduler.app.backend.aREST.Repo.ScheduleRepo;
import com.scheduler.app.backend.aREST.Repo.TaskRepo;
// crud on database. does not manipulate scheduler
@Service
public class TaskService extends Base{
    private final TaskRepo service;
    private SchedulerTask sche=new SchedulerTask();
    private final ScheduleRepo serviceSch;
    public TaskService(TaskRepo service, ScheduleRepo serviceSch) {
        this.service = service;
        this.serviceSch = serviceSch;
    }
    public Task saveTask(Task task){
        return service.save(task);
    }
    public Task addTask(Task entry){
        LocalDateTime setDt=addDuration("0:01");
        entry.setScheduledTime(setDt);
        Task add=service.save(entry);
        addToScheduler();
        return add;
    }
    public Task setTaskSchedule(Task task){
        if(task.getSchedule().getStartup()){
            LocalDateTime currenDateTime = LocalDateTime.now();
            task.setScheduledTime(currenDateTime);
            task.setActive(true);
        }
        if(task.getSchedule().getRepeatTask()){
            LocalDateTime dateTime=addDuration(task.getSchedule().getTime());
            task.setScheduledTime(dateTime);
            task.setActive(true);
        }
        service.save(task);
        addToScheduler();
        return task;
    }
    public void deleteTask(Task task){
        if(task.getOneTimeJob()){
            service.delete(task);
        }
        if(task.getSchedule()!=null){
            task.setActive(false);
            service.save(task);
        }
        addToScheduler();   
    }
    // modify task when finished in scheduler
    public void modifyTaskFromScheduler(Task task){
        if(!task.getOneTimeJob()){
            if(task.getSchedule().getStartup()){
                task.setActive(false);
            }
            if(task.getSchedule().getRepeatTask()){
                LocalDateTime schedule=addDuration(task.getSchedule().getTime());
                task.setScheduledTime(schedule);
            }
            // start next task
            if(task.getSchedule().getNextTask()!=0){
                Schedule schedule=serviceSch.getReferenceById(task.getSchedule().getNextTask());
                if(schedule!=null){
                    setTaskSchedule(schedule.getTask());
                }
            }
            service.save(task);
            addToScheduler();
        }else
        {
            deleteTask(task);
        }
    }
    // delete all task in queue and running
    public void masterDelete(){
        List<Task> list=service.getAllTaskAct(true);
        for(int i=0; i<list.size(); i++){
            Task update=list.get(i);
            update.setActive(false);
            service.save(update);
        }
        //service.deleteAll();
        sche.clearRunningTask();
        addToScheduler();
    }
    public void clearQueue(){
        service.deleteAll();
        sche.clearRunningTask();
    }
    public Optional<Task> getTask(long id){
        return service.findById(id);
    }
    // find all task in database
    public List<Task> getAllTask(){
        return service.findAll();
    }
    public List<Task> getAllRunningTask(){
        List<Task> list=sche.getAllRunTask();
        return list;
    }
    public boolean checkCurrentRun(){
        return sche.checkRun();
    }
    // add task to scheduler
    public void addToScheduler(){
        List <Task> list=service.getAllTaskAct(true);
        sche.addToQueue(list);
    }
    
    // schedule time and seconds for task
    private LocalDateTime addDuration(String time){
        String [] timeSplit=time.split(":");
        LocalDateTime currenDateTime = LocalDateTime.now();
        int mins=Integer.valueOf(timeSplit[0]);
        long seconds=Long.parseLong(timeSplit[1]);
        currenDateTime=currenDateTime.plusMinutes(mins).plusSeconds(seconds);
        return currenDateTime;
    }
    
}
