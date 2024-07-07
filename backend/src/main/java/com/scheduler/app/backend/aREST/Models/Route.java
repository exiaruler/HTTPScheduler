package com.scheduler.app.backend.aREST.Models;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.app.backend.aREST.Models.Base.*;
@Entity
public class Route extends ModelBase{
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="device_id")
    private Device device;
    // route of the device
    @Column
    private String route;
    // if the route in use to be scheduled
    @Column 
    private boolean schedule=false;
    // true if the route has modes
    @Column 
    private boolean modes=false;
    // if the route is used to start the device when on
    @Column
    private boolean startRoute=false;
    // list of modes
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "route", cascade ={ CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH })
    private List<Mode> mode;


    public Route() {
    }

    public Route(Device device, String route, boolean schedule, boolean modes, boolean startRoute, List<Mode> mode) {
        this.device = device;
        this.route = route;
        this.schedule = schedule;
        this.modes = modes;
        this.startRoute = startRoute;
        this.mode = mode;
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

    public boolean isSchedule() {
        return this.schedule;
    }

    public boolean getSchedule() {
        return this.schedule;
    }

    public void setSchedule(boolean schedule) {
        this.schedule = schedule;
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

    public boolean isStartRoute() {
        return this.startRoute;
    }

    public boolean getStartRoute() {
        return this.startRoute;
    }

    public void setStartRoute(boolean startRoute) {
        this.startRoute = startRoute;
    }

    public List<Mode> getMode() {
        return this.mode;
    }

    public void setMode(List<Mode> mode) {
        this.mode = mode;
    }

    public Route device(Device device) {
        setDevice(device);
        return this;
    }

    public Route route(String route) {
        setRoute(route);
        return this;
    }

    public Route schedule(boolean schedule) {
        setSchedule(schedule);
        return this;
    }

    public Route modes(boolean modes) {
        setModes(modes);
        return this;
    }

    public Route startRoute(boolean startRoute) {
        setStartRoute(startRoute);
        return this;
    }

    public Route mode(List<Mode> mode) {
        setMode(mode);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Route)) {
            return false;
        }
        Route routes = (Route) o;
        return Objects.equals(device, routes.device) && Objects.equals(route, routes.route) && schedule == routes.schedule && modes == routes.modes && startRoute == routes.startRoute && Objects.equals(mode, routes.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, route, schedule, modes, startRoute, mode);
    }

    @Override
    public String toString() {
        return "{" +
            " device='" + getDevice() + "'" +
            ", route='" + getRoute() + "'" +
            ", schedule='" + isSchedule() + "'" +
            ", modes='" + isModes() + "'" +
            ", startRoute='" + isStartRoute() + "'" +
            ", mode='" + getMode() + "'" +
            "}";
    }
    

}