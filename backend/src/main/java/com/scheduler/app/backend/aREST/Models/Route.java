package com.scheduler.app.backend.aREST.Models;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.Base.ModelBase.ModelBase;
import com.scheduler.app.backend.Command.Models.Command;
import com.scheduler.app.backend.Messaging.Models.BoardTask;
// device functions
@Entity
@Table(indexes = @Index(columnList = "route"))
public class Route extends ModelBase{
    @JsonBackReference
    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="device_id")
    private Device device;
    // route of the device
    @Column
    private String route;
    // true if the route has modes
    @Column 
    private boolean modes=false;
    // electrode
    @Column
    private String electrode="";
    // arest Command route
    @JsonBackReference
    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="command_id")
    private Command command;
    // switch device type
    @Column
    private boolean switchDevice=false;
    // list of modes
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "route", cascade =CascadeType.ALL)
    private List<Mode> mode;
    // Set of pins used for command 
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "route", cascade =CascadeType.ALL)
    private Set<PinsParameter> pins;
    // Board Task
    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "route", cascade = CascadeType.ALL)
    private BoardTask boardAction;
    

    public Route() {
    }

    public Route(Device device, String route, boolean modes, String electrode, Command command, boolean switchDevice, List<Mode> mode, Set<PinsParameter> pins, BoardTask boardAction) {
        this.device = device;
        this.route = route;
        this.modes = modes;
        this.electrode = electrode;
        this.command = command;
        this.switchDevice = switchDevice;
        this.mode = mode;
        this.pins = pins;
        this.boardAction = boardAction;
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

    public String getElectrode() {
        return this.electrode;
    }

    public void setElectrode(String electrode) {
        this.electrode = electrode;
    }

    public Command getCommand() {
        return this.command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public boolean isSwitchDevice() {
        return this.switchDevice;
    }

    public boolean getSwitchDevice() {
        return this.switchDevice;
    }

    public void setSwitchDevice(boolean switchDevice) {
        this.switchDevice = switchDevice;
    }

    public List<Mode> getMode() {
        return this.mode;
    }

    public void setMode(List<Mode> mode) {
        this.mode = mode;
    }

    public Set<PinsParameter> getPins() {
        return this.pins;
    }

    public void setPins(Set<PinsParameter> pins) {
        this.pins = pins;
    }

    public BoardTask getBoardAction() {
        return this.boardAction;
    }

    public void setBoardAction(BoardTask boardAction) {
        this.boardAction = boardAction;
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

    public Route electrode(String electrode) {
        setElectrode(electrode);
        return this;
    }

    public Route command(Command command) {
        setCommand(command);
        return this;
    }

    public Route switchDevice(boolean switchDevice) {
        setSwitchDevice(switchDevice);
        return this;
    }

    public Route mode(List<Mode> mode) {
        setMode(mode);
        return this;
    }

    public Route pins(Set<PinsParameter> pins) {
        setPins(pins);
        return this;
    }

    public Route boardAction(BoardTask boardAction) {
        setBoardAction(boardAction);
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
        return Objects.equals(device, route.device) && Objects.equals(route, route.route) && modes == route.modes && Objects.equals(electrode, route.electrode) && Objects.equals(command, route.command) && switchDevice == route.switchDevice && Objects.equals(mode, route.mode) && Objects.equals(pins, route.pins) && Objects.equals(boardAction, route.boardAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, route, modes, electrode, command, switchDevice, mode, pins, boardAction);
    }

    @Override
    public String toString() {
        return "{" +
            " device='" + getDevice() + "'" +
            ", route='" + getRoute() + "'" +
            ", modes='" + isModes() + "'" +
            ", electrode='" + getElectrode() + "'" +
            ", command='" + getCommand() + "'" +
            ", switchDevice='" + isSwitchDevice() + "'" +
            ", mode='" + getMode() + "'" +
            ", pins='" + getPins() + "'" +
            ", boardAction='" + getBoardAction() + "'" +
            "}";
    }

    
}