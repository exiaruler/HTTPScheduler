package com.scheduler.app.backend.Task.Model;

import java.util.Objects;

import com.scheduler.app.backend.Models.Devices;

public class CompletedTask {
    private Devices device;
    private String statusString;
    private String warning;
    private boolean status;
    private boolean oneTimeJob=true;


    public CompletedTask() {
    }

    public CompletedTask(Devices device, String statusString, String warning, boolean status, boolean oneTimeJob) {
        this.device = device;
        this.statusString = statusString;
        this.warning = warning;
        this.status = status;
        this.oneTimeJob = oneTimeJob;
    }
    

    public boolean isOneTimeJob() {
        return this.oneTimeJob;
    }

    public boolean getOneTimeJob() {
        return this.oneTimeJob;
    }

    public void setOneTimeJob(boolean oneTimeJob) {
        this.oneTimeJob = oneTimeJob;
    }

    public String getWarning() {
        return this.warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
    
    public Devices getDevice() {
        return this.device;
    }

    public void setDevice(Devices device) {
        this.device = device;
    }

    public String getStatusString() {
        return this.statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CompletedTask)) {
            return false;
        }
        CompletedTask completedTask = (CompletedTask) o;
        return Objects.equals(device, completedTask.device) && Objects.equals(statusString, completedTask.statusString) && Objects.equals(warning, completedTask.warning) && status == completedTask.status && oneTimeJob == completedTask.oneTimeJob;
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, statusString, warning, status, oneTimeJob);
    }
    
    
}
