package com.scheduler.app.backend.aREST.Models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.app.backend.aREST.Models.Base.*;

import java.util.*;

import javax.persistence.*;

@Entity
public class Device extends ModelBase{

    // device which the board is belong to
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="board_id")
    private Board board;
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
    // followed arest v2 framework
    @Column
    private boolean frameworkFollowed;
    // custom device framework
    @Column
    private boolean custom;
    // list of routes
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "device", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH })
    private List<Route> routes;
    
    /* 
    // variables
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "variable", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH })
    private List<Variable> variables;
    */

    public Device() {
    }

    public Device(Board board, String deviceName, String state, String warning, int priority, double timeDelay, String type, String subtype, boolean frameworkFollowed, boolean custom, List<Route> routes) {
        this.board = board;
        this.deviceName = deviceName;
        this.state = state;
        this.warning = warning;
        this.priority = priority;
        this.timeDelay = timeDelay;
        this.type = type;
        this.subtype = subtype;
        this.frameworkFollowed = frameworkFollowed;
        this.custom = custom;
        this.routes = routes;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
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

    public boolean isFrameworkFollowed() {
        return this.frameworkFollowed;
    }

    public boolean getFrameworkFollowed() {
        return this.frameworkFollowed;
    }

    public void setFrameworkFollowed(boolean frameworkFollowed) {
        this.frameworkFollowed = frameworkFollowed;
    }

    public boolean isCustom() {
        return this.custom;
    }

    public boolean getCustom() {
        return this.custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public List<Route> getRoutes() {
        return this.routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Device board(Board board) {
        setBoard(board);
        return this;
    }

    public Device deviceName(String deviceName) {
        setDeviceName(deviceName);
        return this;
    }

    public Device state(String state) {
        setState(state);
        return this;
    }

    public Device warning(String warning) {
        setWarning(warning);
        return this;
    }

    public Device priority(int priority) {
        setPriority(priority);
        return this;
    }

    public Device timeDelay(double timeDelay) {
        setTimeDelay(timeDelay);
        return this;
    }

    public Device type(String type) {
        setType(type);
        return this;
    }

    public Device subtype(String subtype) {
        setSubtype(subtype);
        return this;
    }

    public Device frameworkFollowed(boolean frameworkFollowed) {
        setFrameworkFollowed(frameworkFollowed);
        return this;
    }

    public Device custom(boolean custom) {
        setCustom(custom);
        return this;
    }

    public Device routes(List<Route> routes) {
        setRoutes(routes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Device)) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(board, device.board) && Objects.equals(deviceName, device.deviceName) && Objects.equals(state, device.state) && Objects.equals(warning, device.warning) && priority == device.priority && timeDelay == device.timeDelay && Objects.equals(type, device.type) && Objects.equals(subtype, device.subtype) && frameworkFollowed == device.frameworkFollowed && custom == device.custom && Objects.equals(routes, device.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, deviceName, state, warning, priority, timeDelay, type, subtype, frameworkFollowed, custom, routes);
    }

    @Override
    public String toString() {
        return "{" +
            " board='" + getBoard() + "'" +
            ", deviceName='" + getDeviceName() + "'" +
            ", state='" + getState() + "'" +
            ", warning='" + getWarning() + "'" +
            ", priority='" + getPriority() + "'" +
            ", timeDelay='" + getTimeDelay() + "'" +
            ", type='" + getType() + "'" +
            ", subtype='" + getSubtype() + "'" +
            ", frameworkFollowed='" + isFrameworkFollowed() + "'" +
            ", custom='" + isCustom() + "'" +
            ", routes='" + getRoutes() + "'" +
            "}";
    }

   
}