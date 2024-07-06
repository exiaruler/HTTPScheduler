package com.scheduler.Base.ThreadBase;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.HTTPHandle.HttpUtil;
import com.scheduler.app.backend.Task.SchedulerTask;

public class BaseThread extends Thread{
    protected Base base=new Base();
    protected HttpUtil http=new HttpUtil();
    protected SchedulerTask sche=new SchedulerTask();

    @Override
    public void run(){
    }
    // calculate nano seconds
    public long calculateTaskDurationNano(long startTime,long endTime){
        return endTime-startTime;
    }
    // calculate milliseconds
    public double calculateTaskDurationMil(long startTime,long endTime){
        long endTimeNano=calculateTaskDurationNano(startTime, endTime);
        return (double)endTimeNano/ 1_000_000.0;
    }
}
