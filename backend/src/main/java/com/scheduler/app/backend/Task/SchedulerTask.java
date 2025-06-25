package com.scheduler.app.backend.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.scheduler.app.backend.Task.Model.CompletedTask;
import com.scheduler.app.backend.Task.Thread.HttpSchedule;
import com.scheduler.app.backend.Task.Thread.SocketSchedule;
import com.scheduler.app.backend.Task.Thread.CheckRun;
import com.scheduler.app.backend.aREST.Models.*;
import com.scheduler.app.backend.aREST.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;

import com.scheduler.app.backend.Command.Models.Command;
import com.scheduler.app.backend.Messaging.Board.WebSocketHandlerRaw;
import com.scheduler.app.backend.Messaging.Models.BoardTask;
@Component
public class SchedulerTask{
    // setting for queue to start
    public static boolean running=true;
    // start the queue when server starts or reboots
    private boolean start=false;
    // running queue active
    public static boolean queueRun=false;
    @Autowired
    private TaskService service;
    @Autowired
    private BoardService boardService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private RoutesService routeService;
    @Autowired
    private ParameterService parameterService;
    /* 
    @Autowired
    public WebSocketHandlerRaw websocketHandler;
    */
    // task queue
    private static List<Task> queue=new ArrayList<Task>();
    // task running 
    private static List<Task> runningQueue=new ArrayList<Task>();
    // task finished processing 
    private static List<CompletedTask> completeTaskQueue=new ArrayList<CompletedTask>();
    // complete received websocket messages
    private static Map<String,String> completeMessages=new ConcurrentHashMap<>();
    
    @Scheduled(fixedRate = 100)
    public void runSche(){
        if(queue.isEmpty()&&!start){
            boolean active=service.addToScheduler();
            start=true;
        }
        if(running&&!queue.isEmpty()){
            start=true;
            LocalDateTime dt=LocalDateTime.now();
            System.out.println(dt);
            System.out.println("number of task that are pending in the queue "+queue.size());
            System.out.println("number of task that are running "+runningQueue.size());

            for(int i=0; i<queue.size(); i++){
                Task task=queue.get(i);
                LocalDateTime taskDt=task.getScheduledTime();
                if(dt.isAfter(taskDt)||dt.equals(taskDt)){
                    String [] paramsArr={};
                    // add task to running queue if it passes check else let it stick in the queue 
                    if(!checkTaskRunning(task)&&!task.getHttpTask()){
                        Device device=deviceService.getDevice(task.getDeviceId());
                        Route route=routeService.getRouteById(task.getRouteId());
                        Mode mode=routeService.getMode(task.getModeId());
                        paramsArr=parameterService.getParamsArray(task.getModeId());
                        if(device.getBoard().getArestCommand()){
                            updateQueue(task);
                            runningQueue.add(task);
                            queue.remove(i);
                            HttpSchedule thread=new HttpSchedule(task,device,route,mode,paramsArr);
                            thread.start();
                        }
                        if(device.getBoard().getSocket()){
                            String wsId=device.getBoard().getWebsocketId();
                            BoardTask commandInput=null;
                            updateQueue(task);
                            queue.remove(i);
                            if(task.getOneTimeJob()){
                                
                            }
                            // if it server side command that uses a motor or servo add to running queue
                            if(task.getMotor()){
                                runningQueue.add(task);
                            }
                            /* 
                            if(route.getModes()){
                                commandInput=mode.getBoardAction();
                            }else if(!route.getModes()){
                                commandInput=route.getBoardAction();
                            }
                            */
                            SocketSchedule thread=new SocketSchedule(task, device, route, mode, paramsArr);
                            thread.start();
                        }
                    }
                    // run http task
                    if(task.getHttpTask()){
                        HttpSchedule thread=new HttpSchedule(task,null,null,null,paramsArr);
                        thread.start();
                        queue.remove(i);
                    }
                    
                }

            }
        }
    }
    @Scheduled(fixedRate = 60000)
    public void checkRunningQueue(){
        if(queueRun){
            LocalDateTime dt=LocalDateTime.now();
            for(int i=0; i<runningQueue.size(); i++){
                Task task=queue.get(i);
                LocalDateTime taskDt=task.getScheduledTime();
                if(dt.isAfter(taskDt)&&checkTaskRunning(task)){
                    if(task.getRetry()!=task.getSchedule().getRetries()){
                        runningQueue.remove(i);
                        int tries=task.getRetry();
                        tries++;
                        task.setRetry(tries);
                        queue.add(task);
                        if(runningQueue.size()<0){
                            queueRun=false;
                        }
                    }

                }
            }
        }
    }
    // loop through array to update database
    @Scheduled(fixedRate = 100)
    public void runComplete(){
        if(!completeTaskQueue.isEmpty()){
            System.out.println("Number of completed task in queue "+completeTaskQueue.size());
            for(int i=0; i<completeTaskQueue.size(); i++){
                CompletedTask task=completeTaskQueue.get(i);
                // if task involved a servo or motor remove from running task
                if(task.getTask().isMotor()&&task.getDevice().getBoard().getSocket()){
                    removeRunningTask(task.getTask());
                }
                //updateQueue(task.getTask());
                service.modifyTaskFromScheduler(task.getTask(),task);
                completeTaskQueue.remove(i);
            }
        }
    }

    // check if the device or board is running to avoid clashing or strain on power
    public boolean checkTaskRunning(Task task){
        boolean result=false;
        String tdevice=task.getApplication();
        long tboard=task.getBoard();
        String tsection=task.getSection();
        boolean tmotor=task.getMotor();
        boolean synchronous=false;
        // if command is synchronous by pass check
        if(task.getSchedule().getDevice().getBoard().getArestCommand()){
            Route route=routeService.getRouteById(task.getRouteId());
            //if(route.getCommand().getSynchronous()) synchronous=true;
        }
        if(!synchronous){
            for(int i=0; i<runningQueue.size(); i++){
                Task running=runningQueue.get(i);
                String rdevice=running.getApplication();
                long rboard=running.getBoard();
                String rsection=running.getSection();
                boolean rmotor=running.getMotor();
                if(tdevice.equals(rdevice)){
                    result=true;
                }
                if(tboard==rboard){
                    result=true;
                }
                // validate if servo or any motor device running in that section or board
                if(tsection.equals(rsection)&&rsection!=""){
                    if(tmotor==rmotor&&tdevice.equals(rdevice)&&tboard==rboard&&tsection.equals(rsection)){
                        result=true;
                    }else{
                        if(rmotor){
                            result=true;
                        }
                    }
                }
                
            }
        }
        return result;
    }
    // get priority task 
    private Task getPriority(Task task){
        return task;
    }
    // get all running task
    public List<Task> getAllRunTask(){
        return runningQueue;
    }
    // check if any task running 
    public boolean checkRun(){
        boolean result=false;
        if(runningQueue.size()>0){
            while(!result){
                CheckRun thread=new CheckRun(runningQueue);
                thread.start();
                try {
                    thread.join();
                    boolean check=thread.checkRun(runningQueue);
                    if(check){
                        result=true;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        }
        return result;
    }
    // add list of tasks to the queue from the service 
    public void addToQueue(List<Task> currentList){
        queue=currentList;
    }
    // add to completed task array queue
    public void addToComplete(Device device,boolean status,String state,String warning,boolean complete,Task task){
        //if(complete){
            CompletedTask compTask=new CompletedTask();
            compTask.setDevice(device);
            compTask.setStatus(status);
            compTask.setStatusString(state);
            compTask.setWarning(warning);
            compTask.setTask(task);
            completeTaskQueue.add(compTask);
            System.out.println(completeTaskQueue.size());
        //}
    }
    // add task back into queue if required
    public void failedTask(Task task,Device dev){
        runningQueue.remove(task);
        // retry task before set to fail
        if(task.getRetry()<task.getSchedule().getRetries()){
            int tryA=task.getRetry();
            tryA++;
            task.setRetry(tryA);
            queue.add(task);
            System.out.println("Retry count at "+tryA);
        }else if(task.getRetry()==task.getSchedule().getRetries()){
            // add to complete array to deactive and set it to offline
            if(dev!=null){
                addToComplete(dev,false,"","",false, task);
            }
        }else queue.add(task);
    }
    
    public void updateQueue(Task task){
        task.setActive(false);
        service.saveTask(task);
    }
    public void clearRunningTask(){
        runningQueue.clear();
    }
    public void removeRunningTask(Task task){
        runningQueue.remove(task);
    }

    
}