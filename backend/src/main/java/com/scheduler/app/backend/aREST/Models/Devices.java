package com.scheduler.app.backend.aREST.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.app.backend.aREST.Models.Base.*;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.*;

@Entity
@Table(name="devices")
public class Devices extends ModelBase{

    // device which the board is belong to
    @JsonManagedReference
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="board_id", nullable=false)
    private Board boardId;
    // name of device
    @Column
    private String deviceName;
    // state which the device is in
    @Column
    private String state;
    // error from device
    @Column
    private String warning;
    // priority for task schedule 
    @Column
    private int priority=1;
    // time delay (mins) for task schedule
    @Column
    private double timeDelay=10;
    // device type optional
    @Column 
    private String type;
    // subtype of device optional
    @Column 
    private String subtype;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Routes> routes=new HashSet<Routes>();

    public Devices() {
    }

    public Devices(Board boardId, String deviceName, String state, String warning, int priority, double timeDelay, String type, String subtype, Set<Routes> routes) {
        this.boardId = boardId;
        this.deviceName = deviceName;
        this.state = state;
        this.warning = warning;
        this.priority = priority;
        this.timeDelay = timeDelay;
        this.type = type;
        this.subtype = subtype;
        this.routes = routes;
    }

    public Board getBoardId() {
        return this.boardId;
    }

    public void setBoardId(Board boardId) {
        this.boardId = boardId;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWarning() {
        return this.warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double getTimeDelay() {
        return this.timeDelay;
    }

    public void setTimeDelay(double timeDelay) {
        this.timeDelay = timeDelay;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return this.subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Set<Routes> getRoutes() {
        return this.routes;
    }

    public void setRoutes(Set<Routes> routes) {
        this.routes = routes;
    }

    public Devices boardId(Board boardId) {
        setBoardId(boardId);
        return this;
    }

    public Devices deviceName(String deviceName) {
        setDeviceName(deviceName);
        return this;
    }

    public Devices state(String state) {
        setState(state);
        return this;
    }

    public Devices warning(String warning) {
        setWarning(warning);
        return this;
    }

    public Devices priority(int priority) {
        setPriority(priority);
        return this;
    }

    public Devices timeDelay(double timeDelay) {
        setTimeDelay(timeDelay);
        return this;
    }

    public Devices type(String type) {
        setType(type);
        return this;
    }

    public Devices subtype(String subtype) {
        setSubtype(subtype);
        return this;
    }

    public Devices routes(Set<Routes> routes) {
        setRoutes(routes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Devices)) {
            return false;
        }
        Devices devices = (Devices) o;
        return Objects.equals(boardId, devices.boardId) && Objects.equals(deviceName, devices.deviceName) && Objects.equals(state, devices.state) && Objects.equals(warning, devices.warning) && priority == devices.priority && timeDelay == devices.timeDelay && Objects.equals(type, devices.type) && Objects.equals(subtype, devices.subtype) && Objects.equals(routes, devices.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, deviceName, state, warning, priority, timeDelay, type, subtype, routes);
    }

    @Override
    public String toString() {
        return "{" +
            " boardId='" + getBoardId() + "'" +
            ", deviceName='" + getDeviceName() + "'" +
            ", state='" + getState() + "'" +
            ", warning='" + getWarning() + "'" +
            ", priority='" + getPriority() + "'" +
            ", timeDelay='" + getTimeDelay() + "'" +
            ", type='" + getType() + "'" +
            ", subtype='" + getSubtype() + "'" +
            ", routes='" + getRoutes() + "'" +
            "}";
    }
    
    
    
}