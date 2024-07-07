package com.scheduler.app.backend.aREST.Models;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scheduler.app.backend.aREST.Models.Base.*;
@Entity
public class Mode extends ModelBase{
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="route_id")
    private Route route;
    @Column
    private String mode;
    @Column 
    private boolean selectedMode;
    

    public Mode() {
    }

    public Mode(Route route, String mode, boolean selectedMode) {
        this.route = route;
        this.mode = mode;
        this.selectedMode = selectedMode;
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

    public boolean isSelectedMode() {
        return this.selectedMode;
    }

    public boolean getSelectedMode() {
        return this.selectedMode;
    }

    public void setSelectedMode(boolean selectedMode) {
        this.selectedMode = selectedMode;
    }

    public Mode route(Route route) {
        setRoute(route);
        return this;
    }

    public Mode mode(String mode) {
        setMode(mode);
        return this;
    }

    public Mode selectedMode(boolean selectedMode) {
        setSelectedMode(selectedMode);
        return this;
    }

    @SuppressWarnings("unlikely-arg-type")
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mode)) {
            return false;
        }
        Mode mode = (Mode) o;
        return Objects.equals(route, mode.route) && Objects.equals(mode, mode.mode) && selectedMode == mode.selectedMode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(route, mode, selectedMode);
    }

    @Override
    public String toString() {
        return "{" +
            " route='" + getRoute() + "'" +
            ", mode='" + getMode() + "'" +
            ", selectedMode='" + isSelectedMode() + "'" +
            "}";
    }


}
    