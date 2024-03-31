package com.scheduler.app.backend.aREST.Models.Base.CustomBase;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import com.scheduler.app.backend.aREST.Models.Board;
import com.scheduler.app.backend.aREST.Models.Devices;
import com.scheduler.app.backend.aREST.Models.Base.*;
@MappedSuperclass
public class TaskBase extends ModelBase {
    // application
    @Column
    private String application;
    // device Id 
    @Column
    private long deviceId;
    // device
    @Column 
    private Devices device;
    // board id 
    @Column
    private long board;
    // board object
    @Column
    private Board boardObj;
    // url
    @Column
    private String url;
    // section 
    @Column
    private String section;
    // task priority
    @Column
    private int priority=1;
    // if motor/servo use in the task
    @Column
    private boolean motor=false;
    // scheduled run time for task
    @Column
    private LocalDateTime scheduledTime=LocalDateTime.now();
    // task to repeat once
    @Column 
    private boolean oneTimeJob=true;

    public TaskBase() {
    }

    public TaskBase(String application, long deviceId, Devices device, long board, Board boardObj, String url, String section, int priority, boolean motor, LocalDateTime scheduledTime, boolean oneTimeJob) {
        this.application = application;
        this.deviceId = deviceId;
        this.device = device;
        this.board = board;
        this.boardObj = boardObj;
        this.url = url;
        this.section = section;
        this.priority = priority;
        this.motor = motor;
        this.scheduledTime = scheduledTime;
        this.oneTimeJob = oneTimeJob;
    }

    public String getApplication() {
        return this.application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public long getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public Devices getDevice() {
        return this.device;
    }

    public void setDevice(Devices device) {
        this.device = device;
    }

    public long getBoard() {
        return this.board;
    }

    public void setBoard(long board) {
        this.board = board;
    }

    public Board getBoardObj() {
        return this.boardObj;
    }

    public void setBoardObj(Board boardObj) {
        this.boardObj = boardObj;
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

    public boolean isMotor() {
        return this.motor;
    }

    public boolean getMotor() {
        return this.motor;
    }

    public void setMotor(boolean motor) {
        this.motor = motor;
    }

    public LocalDateTime getScheduledTime() {
        return this.scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
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

    public TaskBase application(String application) {
        setApplication(application);
        return this;
    }

    public TaskBase deviceId(long deviceId) {
        setDeviceId(deviceId);
        return this;
    }

    public TaskBase device(Devices device) {
        setDevice(device);
        return this;
    }

    public TaskBase board(long board) {
        setBoard(board);
        return this;
    }

    public TaskBase boardObj(Board boardObj) {
        setBoardObj(boardObj);
        return this;
    }

    public TaskBase url(String url) {
        setUrl(url);
        return this;
    }

    public TaskBase section(String section) {
        setSection(section);
        return this;
    }

    public TaskBase priority(int priority) {
        setPriority(priority);
        return this;
    }

    public TaskBase motor(boolean motor) {
        setMotor(motor);
        return this;
    }

    public TaskBase scheduledTime(LocalDateTime scheduledTime) {
        setScheduledTime(scheduledTime);
        return this;
    }

    public TaskBase oneTimeJob(boolean oneTimeJob) {
        setOneTimeJob(oneTimeJob);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskBase)) {
            return false;
        }
        TaskBase taskBase = (TaskBase) o;
        return Objects.equals(application, taskBase.application) && deviceId == taskBase.deviceId && Objects.equals(device, taskBase.device) && board == taskBase.board && Objects.equals(boardObj, taskBase.boardObj) && Objects.equals(url, taskBase.url) && Objects.equals(section, taskBase.section) && priority == taskBase.priority && motor == taskBase.motor && Objects.equals(scheduledTime, taskBase.scheduledTime) && oneTimeJob == taskBase.oneTimeJob;
    }

    @Override
    public int hashCode() {
        return Objects.hash(application, deviceId, device, board, boardObj, url, section, priority, motor, scheduledTime, oneTimeJob);
    }

    @Override
    public String toString() {
        return "{" +
            " application='" + getApplication() + "'" +
            ", deviceId='" + getDeviceId() + "'" +
            ", device='" + getDevice() + "'" +
            ", board='" + getBoard() + "'" +
            ", boardObj='" + getBoardObj() + "'" +
            ", url='" + getUrl() + "'" +
            ", section='" + getSection() + "'" +
            ", priority='" + getPriority() + "'" +
            ", motor='" + isMotor() + "'" +
            ", scheduledTime='" + getScheduledTime() + "'" +
            ", oneTimeJob='" + isOneTimeJob() + "'" +
            "}";
    }
   

}
