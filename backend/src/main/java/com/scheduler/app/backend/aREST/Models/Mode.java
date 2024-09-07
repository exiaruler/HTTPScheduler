package com.scheduler.app.backend.aREST.Models;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
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


    public Mode() {
    }

    public Mode(Route route, String mode) {
        this.route = route;
        this.mode = mode;
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

    public Mode route(Route route) {
        setRoute(route);
        return this;
    }

    public Mode mode(String mode) {
        setMode(mode);
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
        return Objects.equals(route, mode.route) && Objects.equals(mode, mode.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route, mode);
    }

    @Override
    public String toString() {
        return "{" +
            " route='" + getRoute() + "'" +
            ", mode='" + getMode() + "'" +
            "}";
    }

}
    