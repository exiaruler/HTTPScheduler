package com.scheduler.app.backend.aREST.Models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scheduler.Base.ModelBase.ModelBase;
import java.util.Objects;
// schedule http request tasks
@Entity
public class Schedule extends ModelBase {
    // name
    @Column
    private String name;
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
    // link to task
    //@PrimaryKeyJoinColumn
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "schedule", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private Task task;
    // link to device
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="device_id")
    private Device device;


    public Schedule() {
    }

    public Schedule(String name, String time, boolean repeatTask, boolean startup, long nextTask, Task task, Device device) {
        this.name = name;
        this.time = time;
        this.repeatTask = repeatTask;
        this.startup = startup;
        this.nextTask = nextTask;
        this.task = task;
        this.device = device;
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

    public Schedule task(Task task) {
        setTask(task);
        return this;
    }

    public Schedule device(Device device) {
        setDevice(device);
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
        return Objects.equals(name, schedule.name) && Objects.equals(time, schedule.time) && repeatTask == schedule.repeatTask && startup == schedule.startup && nextTask == schedule.nextTask && Objects.equals(task, schedule.task) && Objects.equals(device, schedule.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, time, repeatTask, startup, nextTask, task, device);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", time='" + getTime() + "'" +
            ", repeatTask='" + isRepeatTask() + "'" +
            ", startup='" + isStartup() + "'" +
            ", nextTask='" + getNextTask() + "'" +
            ", task='" + getTask() + "'" +
            ", device='" + getDevice() + "'" +
            "}";
    }
    
}
