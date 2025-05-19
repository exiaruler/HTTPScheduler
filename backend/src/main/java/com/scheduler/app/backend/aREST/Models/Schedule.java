package com.scheduler.app.backend.aREST.Models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scheduler.Base.ModelBase.ModelBase;
// schedule http request and device tasks
@Entity
public class Schedule extends ModelBase {
    // name
    @Column
    private String name="";
    // time interval
    @Column
    private String time="00:00";
    // if repeating
    @Column
    private boolean repeatTask;
    // task for startup
    @Column
    private boolean startup;
    // next task after schedule task is completed
    @Column
    private long nextTask;
    // mode
    @Column 
    private String mode="";
    // retry connection
    @Column
    private int retries=3;
    // url
    @Column
    private String url="";
    // link to task
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "schedule", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private Task task;
    // link to device
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="device_id")
    private Device device;
    // link route from device
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="route_id")
    private Route route;
    

    public Schedule() {
    }

    public Schedule(String name, String time, boolean repeatTask, boolean startup, long nextTask, String mode, int retries, String url, Task task, Device device, Route route) {
        this.name = name;
        this.time = time;
        this.repeatTask = repeatTask;
        this.startup = startup;
        this.nextTask = nextTask;
        this.mode = mode;
        this.retries = retries;
        this.url = url;
        this.task = task;
        this.device = device;
        this.route = route;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRepeatTask() {
        return this.repeatTask;
    }

    public boolean getRepeatTask() {
        return this.repeatTask;
    }

    public void setRepeatTask(boolean repeatTask) {
        this.repeatTask = repeatTask;
    }

    public boolean isStartup() {
        return this.startup;
    }

    public boolean getStartup() {
        return this.startup;
    }

    public void setStartup(boolean startup) {
        this.startup = startup;
    }

    public long getNextTask() {
        return this.nextTask;
    }

    public void setNextTask(long nextTask) {
        this.nextTask = nextTask;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getRetries() {
        return this.retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Device getDevice() {
        return this.device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Route getRoute() {
        return this.route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Schedule name(String name) {
        setName(name);
        return this;
    }

    public Schedule time(String time) {
        setTime(time);
        return this;
    }

    public Schedule repeatTask(boolean repeatTask) {
        setRepeatTask(repeatTask);
        return this;
    }

    public Schedule startup(boolean startup) {
        setStartup(startup);
        return this;
    }

    public Schedule nextTask(long nextTask) {
        setNextTask(nextTask);
        return this;
    }

    public Schedule mode(String mode) {
        setMode(mode);
        return this;
    }

    public Schedule retries(int retries) {
        setRetries(retries);
        return this;
    }

    public Schedule url(String url) {
        setUrl(url);
        return this;
    }

    public Schedule task(Task task) {
        setTask(task);
        return this;
    }

    public Schedule device(Device device) {
        setDevice(device);
        return this;
    }

    public Schedule route(Route route) {
        setRoute(route);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Schedule)) {
            return false;
        }
        Schedule schedule = (Schedule) o;
        return Objects.equals(name, schedule.name) && Objects.equals(time, schedule.time) && repeatTask == schedule.repeatTask && startup == schedule.startup && nextTask == schedule.nextTask && Objects.equals(mode, schedule.mode) && retries == schedule.retries && Objects.equals(url, schedule.url) && Objects.equals(task, schedule.task) && Objects.equals(device, schedule.device) && Objects.equals(route, schedule.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, time, repeatTask, startup, nextTask, mode, retries, url, task, device, route);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", time='" + getTime() + "'" +
            ", repeatTask='" + isRepeatTask() + "'" +
            ", startup='" + isStartup() + "'" +
            ", nextTask='" + getNextTask() + "'" +
            ", mode='" + getMode() + "'" +
            ", retries='" + getRetries() + "'" +
            ", url='" + getUrl() + "'" +
            ", task='" + getTask() + "'" +
            ", device='" + getDevice() + "'" +
            ", route='" + getRoute() + "'" +
            "}";
    }

}
