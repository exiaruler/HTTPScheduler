package com.scheduler.app.backend.Task;
import java.util.ArrayList;
import java.util.List;

import com.scheduler.app.backend.Task.Model.CompletedTask;
import com.scheduler.app.backend.Task.Thread.HttpSchedule;
import com.scheduler.app.backend.Task.Thread.CheckRun;
import com.scheduler.app.backend.aREST.Models.*;
import com.scheduler.app.backend.aREST.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
@Component
public class SchedulerTask{
    // setting for queue to start
    public static boolean running=true;
    private boolean start=false;
    
    @Autowired
    private TaskService service;
    @Autowired
    private BoardService boardService;
    @Autowired
    private DeviceService deviceService;
    // task queue
    private static List<Task> queue=new ArrayList<Task>();
    // task running 
    private static List<Task> runningQueue=new ArrayList<Task>();
    // task finished processing 
    private static List<CompletedTask> completeTaskQueue=new ArrayList<CompletedTask>();
    @Scheduled(fixedRate = 100)
    public void runSche(){
        if(queue.isEmpty()&&!start){
            service.addToScheduler();
            start=true;
        }
        if(running&&!queue.isEmpty()){
            LocalDateTime dt=LocalDateTime.now();
            System.out.println(dt);
            System.out.println("number of task that are pending in the queue "+queue.size());
            System.out.println("number of task that are running "+runningQueue.size());

            for(int i=0; i<queue.size(); i++){
                Task task=queue.get(i);
                long taskId=task.getId();
                LocalDateTime taskDt=task.getScheduledTime();
                if(dt.isAfter(taskDt)||dt.equals(taskDt)){
                    // add task to running queue if it passes check else let it stick in the queue 
                    if(!checkTaskRunning(task)){
                        Device device=deviceService.getDevice(task.getDeviceId());
                        runningQueue.add(task);
                        HttpSchedule thread=new HttpSchedule(task,device);
                        thread.start();
                        queue.remove(i);
                        updateQueue(taskId);
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
                deviceService.updateDeviceAfterAction(task);
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
    public void addToComplete(Device device,boolean status,String state,String warning,boolean complete){
        if(complete){
            CompletedTask compTask=new CompletedTask();
            compTask.setDevice(device);
            compTask.setStatus(status);
            compTask.setStatusString(state);
            compTask.setWarning(warning);
            completeTaskQueue.add(compTask);
            System.out.println(completeTaskQueue.size());
        }
        
    }
    // add task back into queue if required
    public void failedTask(Task task){
        queue.add(task);
    }
   
    public void updateQueue(long id){
        service.deleteTask(id);
        service.addToScheduler();
    }
    public void clearRunningTask(){
        runningQueue.clear();
    }
    public void removeRunningTask(Task task){
        for(int i=0; i<runningQueue.size(); i++){
            if(runningQueue.get(i).equals(task)){
                runningQueue.remove(i);
                break;
            }
        }
    }

    
}