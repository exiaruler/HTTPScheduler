package com.scheduler.app.backend.aREST.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.scheduler.Base.Base;
import com.scheduler.app.backend.aREST.Models.Schedule;
import com.scheduler.app.backend.aREST.Models.Task;
import com.scheduler.app.backend.aREST.Repo.ScheduleRepo;
import com.scheduler.app.backend.aREST.Repo.TaskRepo;
@Service
public class ScheduleService extends Base{
    private final ScheduleRepo service;
    public final TaskService taskService;
    private final TaskRepo taskRepo;
    public ScheduleService(ScheduleRepo schedule, TaskService taskService, TaskRepo taskRepo){
        this.service = schedule;
        this.taskService = taskService;
        this.taskRepo = taskRepo;
    }
    public List<Schedule> getAllSchedule(){
        return service.findAll();
    }
    public Schedule getSchedule(long id){
        return service.getReferenceById(id);
    }
    public Schedule addHttpTask(String name,String time,boolean repeat,boolean startup,String request){
        Schedule scheduleTask=new Schedule();
        Task taskSche=new Task();
        scheduleTask.setName(name);
        if(startup){
            scheduleTask.setStartup(startup);
        }else
        {
            scheduleTask.setTime(time);
            scheduleTask.setRepeatTask(repeat);
        }
        //
        taskSche.setUrl(request);
        taskSche.setHttpTask(true);
        taskSche.application(name);
        taskSche.oneTimeJob(false);
        taskSche.setSchedule(scheduleTask);
        scheduleTask.setTask(taskSche);
        service.save(scheduleTask);
        return scheduleTask;
    }
    // start task schedule start task
    public boolean startupTask(long id){
        Schedule schedule=service.getReferenceById(id);
        boolean success=false;
        if(schedule!=null){
            Task task=schedule.getTask();
            taskService.setTaskSchedule(task);
            success=true;
        }
        return success;
    }
    public boolean testTask(long id){
        boolean success=false;
        Schedule schedule=service.getReferenceById(id);
        if(schedule!=null){
            Task task=schedule.getTask();
            taskService.setTaskSchedule(task);
            success=true;
        }
        return success;
    }
}
