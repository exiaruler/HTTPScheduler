package com.scheduler.app.backend.aREST.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.scheduler.Base.Base;
import com.scheduler.app.backend.aREST.Models.Device;
import com.scheduler.app.backend.aREST.Models.Mode;
import com.scheduler.app.backend.aREST.Models.Route;
import com.scheduler.app.backend.aREST.Models.Schedule;
import com.scheduler.app.backend.aREST.Models.Task;
import com.scheduler.app.backend.aREST.Repo.ScheduleRepo;
import com.scheduler.app.backend.aREST.Repo.TaskRepo;
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
    public Schedule addSchedule(String name,String time,boolean repeat,boolean startup,String request,long deviceId,long routeId,long modeId){
        Schedule scheduleTask=new Schedule();
        Task taskSche=new Task();
        scheduleTask.setName(name);
        scheduleTask.setTime(time);
        // save task with device and route
        if(deviceId!=0&&routeId!=0){
            Device device=deviceService.getDevice(deviceId);
            List <Schedule> deviceSchList=device.getSchedules();
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
                        if(route.getModes()){
                            Mode mode=routeService.getMode(modeId);
                            if(mode!=null){
                                scheduleTask.setMode(mode.getMode());
                            }
                        }
                        // save task
                        taskSche.setApplication(name);
                        taskSche.setDeviceId(deviceId);
                        taskSche.setBoard(device.getBoard().getId());
                        String urlTask=taskService.createRouteUrl(device.getBoard().getIp(),route.getRoute(),scheduleTask.getMode());
                        taskSche.setUrl(urlTask);
                        taskSche.oneTimeJob(false);
                        taskSche.setSchedule(scheduleTask);
                        deviceSchList.add(scheduleTask);
                        device.setSchedules(deviceSchList);
                        // set motor 
                        scheduleTask.setTask(taskSche);
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
            taskSche.setUrl(request);
            taskSche.setHttpTask(true);
            taskSche.application(name);
            taskSche.oneTimeJob(false);
            taskSche.setSchedule(scheduleTask);
            scheduleTask.setTask(taskSche);
        }
        service.save(scheduleTask);
        return scheduleTask;
    }
    public Schedule updateScheduleTest(Task task){
        Schedule sche=service.getReferenceById(task.getSchedule().getId());
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
