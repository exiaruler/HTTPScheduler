package com.scheduler.app.backend.aREST.Models;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.scheduler.app.backend.aREST.Models.Base.*;
@Entity
public class Routes extends ModelBase{
    @ManyToOne
    @JoinColumn(name="device_id", nullable=false)
    private Devices device;
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
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    private Set<Mode> mode;

    public Routes() {
    }

    public Routes(Devices device, String route, boolean schedule, boolean modes, boolean startRoute, Set<Mode> mode) {
        this.device = device;
        this.route = route;
        this.schedule = schedule;
        this.modes = modes;
        this.startRoute = startRoute;
        this.mode = mode;
    }


    public Devices getDevice() {
        return this.device;
    }

    public void setDevice(Devices device) {
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

    public Set<Mode> getMode() {
        return this.mode;
    }

    public void setMode(Set<Mode> modeList) {
        this.mode = modeList;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Routes)) {
            return false;
        }
        Routes routes = (Routes) o;
        return Objects.equals(device, routes.device) && Objects.equals(route, routes.route) && schedule == routes.schedule && modes == routes.modes && startRoute == routes.startRoute && Objects.equals(mode, routes.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, route, schedule, modes, startRoute, mode);
    }


}