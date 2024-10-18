package com.scheduler.app.backend.aREST.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.Task.SchedulerTask;
import com.scheduler.app.backend.Task.Model.CompletedTask;
import com.scheduler.app.backend.aREST.Models.*;
import com.scheduler.app.backend.aREST.Repo.ScheduleRepo;
import com.scheduler.app.backend.aREST.Repo.TaskRepo;
// crud on database. does not manipulate scheduler
@Service
public class TaskService extends Base{
    private final TaskRepo service;
    private SchedulerTask sche=new SchedulerTask();
    private final ScheduleRepo serviceSch;
    private final DeviceService deviceService;
    public TaskService(TaskRepo service, ScheduleRepo serviceSch, DeviceService deviceService) {
        this.service = service;
        this.serviceSch = serviceSch;
        this.deviceService = deviceService;
    }
    public Task saveTask(Task task){
        return service.save(task);
    }
    public Task addTask(Task entry){
        LocalDateTime setDt=addDuration("0:01");
        entry.setScheduledTime(setDt);
        entry.setActive(true);
        Task add=service.save(entry);
        addToScheduler();
        return add;
    }
    public Task setTaskSchedule(Task task){
        LocalDateTime dateTime=addDuration(task.getSchedule().getTime());
        task.setScheduledTime(dateTime);
        task.setActive(true);
        service.save(task);
        addToScheduler();
        return task;
    }
    public void deleteTask(Task task){
        if(task.getOneTimeJob()){
            service.delete(task);
        }
        if(task.getSchedule()!=null){
            //task.setActive(false);
            //service.save(task);
        }
        //addToScheduler();   
    }
    // modify task when finished in scheduler
    public void modifyTaskFromScheduler(Task task,CompletedTask complete){
        // update device state and warning
        if(complete.getDevice()!=null){
            String state="";
            String warning="";
            if(complete.getWarning()!=""&&!complete.getStatus()){
                warning=complete.getWarning();
            }else{
                if(complete.getStatusString()!=""){
                    state=complete.getStatusString();
                }else{
                    state="Offline";
                }
            }
            task.getSchedule().getDevice().setState(state);
            task.getSchedule().getDevice().setWarning(warning);
        }
        task.setRetry(0);
        if(!task.getOneTimeJob()){
            if(complete.getStatus()){
                if(task.getSchedule().getStartup()){
                    task.setActive(false);
                }
                
                if(task.getSchedule().getRepeatTask()){
                    LocalDateTime schedule=addDuration(task.getSchedule().getTime());
                    task.setScheduledTime(schedule);
                    task.setActive(true);
                }
                // start next task
                if(task.getSchedule().getNextTask()!=0){
                    Schedule schedule=serviceSch.getReferenceById(task.getSchedule().getNextTask());
                    if(schedule!=null){
                        setTaskSchedule(schedule.getTask());
                    }
                }
            // disable schedule task and update device state to offline
            }else if(!complete.getStatus()){
                task.setActive(false);
            }
            service.save(task);
            addToScheduler();
        }else
        {
            deviceService.updateDeviceAfterAction(complete,complete.getDevice());
            service.save(task);
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
        addToScheduler();
        sche.clearRunningTask();
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
    // find task by active
    public List<Task> getAllTaskStat(boolean status){
        return service.getAllTaskAct(status);
    }
    public List<Task> getAllRunningTask(){
        List<Task> list=sche.getAllRunTask();
        return list;
    }
    public boolean checkCurrentRun(){
        return sche.checkRun();
    }
    // add task to scheduler
    public boolean addToScheduler(){
        boolean results=false;
        List <Task> list=service.getAllTaskAct(true);
        if(list.size()>0) results=true;
        sche.addToQueue(list);
        return results;
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
    public String createRouteUrl(String ip,String route,String param){
        if(route==""||ip=="") return "";
        String requestUrl="http://"+ip+"/"+route;
        if(param!=""){
            requestUrl=requestUrl+"?params="+param;
        }else requestUrl=requestUrl+"?params=1";
        return requestUrl;
    }
}
