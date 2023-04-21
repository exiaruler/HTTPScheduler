package com.scheduler.app.backend.Models;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.scheduler.app.backend.Models.Base.*;
@Entity
public class Task extends ModelBase {
    @Column
    private String application;
    @Column
    private long deviceId;
    @Column
    private long board;
    @Column
    private String url;
    @Column
    private String section;
    @Column
    private int priority=1;
    @Column
    private boolean motor=false;
    @Column
    private LocalDateTime scheduledTime=LocalDateTime.now();
    

    public Task() {
    }

    public Task(String application, long deviceId, long board, String url, String section, int priority, boolean motor, LocalDateTime scheduledTime) {
        this.application = application;
        this.deviceId = deviceId;
        this.board = board;
        this.url = url;
        this.section = section;
        this.priority = priority;
        this.motor = motor;
        this.scheduledTime = scheduledTime;
    }


    public long getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }
    
    public boolean isMotor() {
        return this.motor;
    }

    public boolean getMotor() {
        return this.motor;
    }

    public void setMotor(boolean motor) {
        this.motor = motor;
    }
    
    public String getApplication() {
        return this.application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public long getBoard() {
        return this.board;
    }

    public void setBoard(long board) {
        this.board = board;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return this.section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getScheduledTime() {
        return this.scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(application, task.application) && deviceId == task.deviceId && board == task.board && Objects.equals(url, task.url) && Objects.equals(section, task.section) && priority == task.priority && motor == task.motor && Objects.equals(scheduledTime, task.scheduledTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(application, deviceId, board, url, section, priority, motor, scheduledTime);
    }



}