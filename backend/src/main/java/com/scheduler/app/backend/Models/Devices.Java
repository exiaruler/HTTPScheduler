package com.scheduler.app.backend.Models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.app.backend.Models.Base.*;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Devices extends ModelBase{

    @JsonManagedReference
    @ManyToOne(optional = false)
    private Board boardId;

    @Column
    private String deviceName;
    @Column
    private String state;
    @Column
    private String warning;
    @Column
    private int priority=1;
    @Column
    private double timeDelay=10;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Routes> routes;

    public Devices() {
    }

    public Devices(Board boardId, String deviceName, String state, String warning, int priority, double timeDelay, List<Routes> routes) {
        this.boardId = boardId;
        this.deviceName = deviceName;
        this.state = state;
        this.warning = warning;
        this.priority = priority;
        this.timeDelay = timeDelay;
        this.routes = routes;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public List<Routes> getRoutes() {
        return this.routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }
    
    public Board getBoardId() {
        return this.boardId;
    }

    public void setBoardId(Board boardId) {
        this.boardId = boardId;
    }
    

    public double getTimeDelay() {
        return this.timeDelay;
    }

    public void setTimeDelay(double timeDelay) {
        this.timeDelay = timeDelay;
    }


    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public String getDevice() {
        return this.deviceName;
    }

    public void setDevice(String device) {
        this.deviceName = device;
    }

    public String getWarning() {
        return this.warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Devices)) {
            return false;
        }
        Devices devices = (Devices) o;
        return Objects.equals(boardId, devices.boardId) && Objects.equals(deviceName, devices.deviceName) && Objects.equals(state, devices.state) && Objects.equals(warning, devices.warning) && priority == devices.priority && timeDelay == devices.timeDelay && Objects.equals(routes, devices.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, deviceName, state, warning, priority, timeDelay, routes);
    }

    
}