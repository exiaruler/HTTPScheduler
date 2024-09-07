package com.scheduler.app.backend.aREST.Models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.Base.ModelBase.ModelBase;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(columnList = "name"))
public class Device extends ModelBase{

    // device which the board is belong to
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="board_id")
    private Board board;
    // name of device
    @Column
    private String name;
    // state which the device is in
    @Column
    private String state;
    // error from device
    @Column
    private String warning;
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
    // list of schedule task
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "device", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH })
    private List<Schedule> schedules;
    // list of routes used for schedule
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "device", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH })
    private List<Route> routesSchedule;
    // list of components that device uses. mapping purpose
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "device", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH })
    private List<Component> components;
    /* 
    // variables
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "variable", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH })
    private List<Variable> variables;
    */


    public Device() {
    }

    public Device(Board board, String name, String state, String warning, String type, String subtype, boolean frameworkFollowed, boolean custom, List<Route> routes, List<Schedule> schedules, List<Route> routesSchedule, List<Component> components) {
        this.board = board;
        this.name = name;
        this.state = state;
        this.warning = warning;
        this.type = type;
        this.subtype = subtype;
        this.frameworkFollowed = frameworkFollowed;
        this.custom = custom;
        this.routes = routes;
        this.schedules = schedules;
        this.routesSchedule = routesSchedule;
        this.components = components;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Schedule> getSchedules() {
        return this.schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Route> getRoutesSchedule() {
        return this.routesSchedule;
    }

    public void setRoutesSchedule(List<Route> routesSchedule) {
        this.routesSchedule = routesSchedule;
    }

    public List<Component> getComponents() {
        return this.components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public Device board(Board board) {
        setBoard(board);
        return this;
    }

    public Device name(String name) {
        setName(name);
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

    public Device schedules(List<Schedule> schedules) {
        setSchedules(schedules);
        return this;
    }

    public Device routesSchedule(List<Route> routesSchedule) {
        setRoutesSchedule(routesSchedule);
        return this;
    }

    public Device components(List<Component> components) {
        setComponents(components);
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
        return Objects.equals(board, device.board) && Objects.equals(name, device.name) && Objects.equals(state, device.state) && Objects.equals(warning, device.warning) && Objects.equals(type, device.type) && Objects.equals(subtype, device.subtype) && frameworkFollowed == device.frameworkFollowed && custom == device.custom && Objects.equals(routes, device.routes) && Objects.equals(schedules, device.schedules) && Objects.equals(routesSchedule, device.routesSchedule) && Objects.equals(components, device.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, name, state, warning, type, subtype, frameworkFollowed, custom, routes, schedules, routesSchedule, components);
    }

    @Override
    public String toString() {
        return "{" +
            " board='" + getBoard() + "'" +
            ", name='" + getName() + "'" +
            ", state='" + getState() + "'" +
            ", warning='" + getWarning() + "'" +
            ", type='" + getType() + "'" +
            ", subtype='" + getSubtype() + "'" +
            ", frameworkFollowed='" + isFrameworkFollowed() + "'" +
            ", custom='" + isCustom() + "'" +
            ", routes='" + getRoutes() + "'" +
            ", schedules='" + getSchedules() + "'" +
            ", routesSchedule='" + getRoutesSchedule() + "'" +
            ", components='" + getComponents() + "'" +
            "}";
    }


}