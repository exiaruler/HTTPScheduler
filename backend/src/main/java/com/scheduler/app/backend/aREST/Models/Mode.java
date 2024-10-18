package com.scheduler.app.backend.aREST.Models;
import java.util.List;
import java.util.Objects;

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
@Entity
@Table(indexes = @Index(columnList = "mode"))
public class Mode extends ModelBase{
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="route_id")
    private Route route;
    // mode
    @Column
    private String mode;
    // switch off Mode
    @Column
    private boolean switchOff=false;    
    // aREST command
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "mode", cascade ={ CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH })
    private List<Parameter> params;

    public Mode() {
    }

    public Mode(Route route, String mode, boolean switchOff, List<Parameter> params) {
        this.route = route;
        this.mode = mode;
        this.switchOff = switchOff;
        this.params = params;
    }

    public Route getRoute() {
        return this.route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isSwitchOff() {
        return this.switchOff;
    }

    public boolean getSwitchOff() {
        return this.switchOff;
    }

    public void setSwitchOff(boolean switchOff) {
        this.switchOff = switchOff;
    }

    public List<Parameter> getParams() {
        return this.params;
    }

    public void setParams(List<Parameter> params) {
        this.params = params;
    }

    public Mode route(Route route) {
        setRoute(route);
        return this;
    }

    public Mode mode(String mode) {
        setMode(mode);
        return this;
    }

    public Mode switchOff(boolean switchOff) {
        setSwitchOff(switchOff);
        return this;
    }

    public Mode params(List<Parameter> params) {
        setParams(params);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mode)) {
            return false;
        }
        Mode mode = (Mode) o;
        return Objects.equals(route, mode.route) && Objects.equals(mode, mode.mode) && switchOff == mode.switchOff && Objects.equals(params, mode.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route, mode, switchOff, params);
    }

    @Override
    public String toString() {
        return "{" +
            " route='" + getRoute() + "'" +
            ", mode='" + getMode() + "'" +
            ", switchOff='" + isSwitchOff() + "'" +
            ", params='" + getParams() + "'" +
            "}";
    }
    
    
}
    