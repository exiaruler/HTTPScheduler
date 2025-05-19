package com.scheduler.app.backend.aREST.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.aREST.Models.Device;
import com.scheduler.app.backend.aREST.Models.Mode;
import com.scheduler.app.backend.aREST.Models.Route;
import com.scheduler.app.backend.aREST.Models.Schedule;
import com.scheduler.app.backend.aREST.Models.Task;
import com.scheduler.app.backend.aREST.Repo.ScheduleRepo;
@Service
public class ScheduleService extends Base{
    private final ScheduleRepo service;
    public final TaskService taskService;
    public final DeviceService deviceService;
    public final RoutesService routeService;
    public ScheduleService(ScheduleRepo schedule, TaskService taskService, DeviceService deviceService, RoutesService routeService){
        this.service = schedule;
        this.taskService = taskService;
        this.deviceService = deviceService;
        this.routeService = routeService;
    }
    public Schedule newRecord(){
        Schedule record=new Schedule();
        return record;
    }
    public List<Schedule> getAllSchedule(){
        return service.findAll();
    }
    public Schedule getSchedule(long id){
        return service.getReferenceById(id);
    }
    public void deleteSchedule(long id){
        service.deleteById(id);
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
      
    public Task createTask(long id,String application,String url,long routeId,long modeId,boolean hasMotor,Schedule schedule,Device device,Route route){
        Task tsk=null;
        if(id>0&&schedule!=null){
            Task task=taskService.getTask(id).get();
            if(task!=null){
                if(device!=null){
                    if(device.getBoard().getArestCommand()){
                        task.setRouteId(routeId);
                        task.setModeId(modeId);
                        task.setMotor(hasMotor);
                    }else if(device.getBoard().getArest()){
                        String urlTask=taskService.createRouteUrl(device.getBoard().getIp(),route.getRoute(),schedule.getMode());
                        task.setUrl(urlTask);
                    }
                }else if(device==null){
                    task.setUrl(url);
                }
                tsk=task;
            }
        }else if(schedule!=null){   
            tsk=new Task();
            tsk.oneTimeJob(false);
            tsk.setApplication(application);
            if(device!=null){
                tsk.setDeviceId(device.getId());
                tsk.setBoard(device.getBoard().getId());
                if(device.getBoard().getArestCommand()){
                    tsk.setRouteId(routeId);
                    tsk.setModeId(modeId);
                    tsk.setMotor(hasMotor);
                }else if(device.getBoard().getArest()){
                    String urlTask=taskService.createRouteUrl(device.getBoard().getIp(),route.getRoute(),schedule.getMode());
                    tsk.setUrl(urlTask);
                }
            }else if(device==null){
                tsk.setUrl(url);
                tsk.setHttpTask(true);
            }
            tsk.setSchedule(schedule);
        }
        return tsk;
    }
        
    public Schedule addSchedule(String name,String time,boolean repeat,boolean startup,String url,long deviceId,long routeId,long modeId){
        Schedule scheduleTask=new Schedule();
        Task taskSche=new Task();
        boolean hasMotor=false;
        scheduleTask.setName(name);
        scheduleTask.setTime(time);
        // save task with device and route
        if(deviceId!=0&&routeId!=0){
            Device device=deviceService.getDevice(deviceId);
            List <Schedule> deviceSchList=new ArrayList<>();
            if(!device.getSchedules().isEmpty()){
                deviceSchList=device.getSchedules();
            } 
            scheduleTask.setDevice(device);
            if(startup){
                scheduleTask.setStartup(startup);
            }else
            {
                scheduleTask.setRepeatTask(repeat);
            }
            if(device!=null){
                List <Route> routeList=device.getRoutes();
                for(int i=0; i<routeList.size(); i++){
                    long id=routeList.get(i).getId();
                    if(routeId==id){
                        Route route=routeList.get(i);
                        scheduleTask.setRoute(route);
                        if(route.getCommand()!=null&&route.getCommand().getHasMotor()) hasMotor=true;
                        if(route.getModes()){
                            Mode mode=routeService.getMode(modeId);
                            if(mode!=null){
                                scheduleTask.setMode(mode.getMode());
                            }
                        }
                        // save task
                        taskSche=createTask(0,name,url,routeId,modeId,hasMotor,scheduleTask,device,route);
                        if(taskSche!=null) scheduleTask.setTask(taskSche);
                        deviceSchList.add(scheduleTask);
                        device.setSchedules(deviceSchList); 
                    }
                }
                
            }
        }else
        // save http task
        {
            if(startup){
                scheduleTask.setStartup(startup);
            }else
            {
                scheduleTask.setRepeatTask(repeat);
            }
            scheduleTask.setUrl(url);
            taskSche.setUrl(url);
            taskSche.setHttpTask(true);
            taskSche.application(name);
            taskSche.oneTimeJob(false);
            taskSche.setSchedule(scheduleTask);
            scheduleTask.setTask(taskSche);
        }
        service.save(scheduleTask);
        return scheduleTask;
    }
  
    public Schedule updateScheduleTest(long id,Task task){
        Schedule sche=service.getReferenceById(id);
        if(sche!=null){
            sche.setTask(task);
            service.save(sche);
        }
        return sche;
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
    // start scheduled tasks when board is turned on
    public void startUpBoard(String ip){
        //
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
