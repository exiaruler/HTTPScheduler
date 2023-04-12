package com.scheduler.app.backend.Models;
import com.scheduler.app.backend.Models.Base.*;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Routes extends ModelBase{
    @ManyToOne(optional = false)
    private Devices device;
    @Column
    private String route;
    @Column 
    private boolean schedule;
    @Column 
    private boolean modes;
    @Column
    private boolean startRoute=false;
    @OneToMany
    private List<Mode> mode;

    public Routes() {
    }

    public Routes(Devices device, String route, boolean schedule, boolean modes, boolean startRoute, List<Mode> mode) {
        this.device = device;
        this.route = route;
        this.schedule = schedule;
        this.modes = modes;
        this.startRoute = startRoute;
        this.mode = mode;
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

    public boolean isModes() {
        return this.modes;
    }

    public boolean getModes() {
        return this.modes;
    }

    public void setModes(boolean modes) {
        this.modes = modes;
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

    public boolean isMode() {
        return this.modes;
    }

    public boolean getMode() {
        return this.modes;
    }

    public void setMode(boolean mode) {
        this.modes = mode;
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