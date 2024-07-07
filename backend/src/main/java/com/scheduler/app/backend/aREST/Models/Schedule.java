package com.scheduler.app.backend.aREST.Models;

import javax.persistence.CascadeType;
import javax.persistence.*;

import com.scheduler.Base.ModelBase.ModelBase;
import java.util.Objects;
// schedule http request tasks
@Entity
public class Schedule extends ModelBase {
    
    @Column
    private String name;
    @Column
    private float time;
    @Column
    private boolean repeat;
    @Column
    private boolean startup;
    @OneToOne(mappedBy = "schedule", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Task task;

    public Schedule() {
    }

    public Schedule(String name, float time, boolean repeat, boolean startup, Task task) {
        this.name = name;
        this.time = time;
        this.repeat = repeat;
        this.startup = startup;
        this.task = task;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTime() {
        return this.time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public boolean isRepeat() {
        return this.repeat;
    }

    public boolean getRepeat() {
        return this.repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
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

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Schedule name(String name) {
        setName(name);
        return this;
    }

    public Schedule time(float time) {
        setTime(time);
        return this;
    }

    public Schedule repeat(boolean repeat) {
        setRepeat(repeat);
        return this;
    }

    public Schedule startup(boolean startup) {
        setStartup(startup);
        return this;
    }

    public Schedule task(Task task) {
        setTask(task);
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
        return Objects.equals(name, schedule.name) && time == schedule.time && repeat == schedule.repeat && startup == schedule.startup && Objects.equals(task, schedule.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, time, repeat, startup, task);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", time='" + getTime() + "'" +
            ", repeat='" + isRepeat() + "'" +
            ", startup='" + isStartup() + "'" +
            ", task='" + getTask() + "'" +
            "}";
    }

}
