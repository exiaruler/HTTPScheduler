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
}
