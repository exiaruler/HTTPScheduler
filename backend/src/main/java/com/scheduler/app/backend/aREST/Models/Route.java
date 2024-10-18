package com.scheduler.app.backend.aREST.Models;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.Base.ModelBase.ModelBase;
import com.scheduler.app.backend.Command.Models.Command;

import java.util.Objects;
@Entity
@Table(indexes = @Index(columnList = "route"))
public class Route extends ModelBase{
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="device_id")
    private Device device;
    // route of the device
    @Column
    private String route;
    // true if the route has modes
    @Column 
    private boolean modes=false;
    // list of modes
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "route", cascade ={ CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH })
    private List<Mode> mode;
    // arest Command route
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="command_id")
    private Command command;



    public Route() {
    }

    public Route(Device device, String route, boolean modes, List<Mode> mode, Command command) {
        this.device = device;
        this.route = route;
        this.modes = modes;
        this.mode = mode;
        this.command = command;
    }

    public Device getDevice() {
        return this.device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getRoute() {
        return this.route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public boolean isModes() {
        return this.modes;
    }

    public boolean getModes() {
        return this.modes;
    }

    public void setModes(boolean modes) {
        this.modes = modes;
    }

    public List<Mode> getMode() {
        return this.mode;
    }

    public void setMode(List<Mode> mode) {
        this.mode = mode;
    }

    public Command getCommand() {
        return this.command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Route device(Device device) {
        setDevice(device);
        return this;
    }

    public Route route(String route) {
        setRoute(route);
        return this;
    }

    public Route modes(boolean modes) {
        setModes(modes);
        return this;
    }

    public Route mode(List<Mode> mode) {
        setMode(mode);
        return this;
    }

    public Route command(Command command) {
        setCommand(command);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Route)) {
            return false;
        }
        Route route = (Route) o;
        return Objects.equals(device, route.device) && Objects.equals(route, route.route) && modes == route.modes && Objects.equals(mode, route.mode) && Objects.equals(command, route.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, route, modes, mode, command);
    }

    @Override
    public String toString() {
        return "{" +
            " device='" + getDevice() + "'" +
            ", route='" + getRoute() + "'" +
            ", modes='" + isModes() + "'" +
            ", mode='" + getMode() + "'" +
            ", command='" + getCommand() + "'" +
            "}";
    }
    
    

}