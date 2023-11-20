package com.scheduler.app.backend.aREST.Models;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.scheduler.app.backend.aREST.Models.Base.*;
@Entity
public class Mode extends ModelBase{
    @ManyToOne
    @JoinColumn(name="route_id", nullable=false)
    private Routes routes;
    @Column
    private String mode;
    @Column 
    private boolean selectedMode;

    public Mode() {
    }


    public Mode(Routes routes, String mode, boolean selectedMode) {
        this.routes = routes;
        this.mode = mode;
        this.selectedMode = selectedMode;
    }

    public Routes getRoutes() {
        return this.routes;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mode)) {
            return false;
        }
        Mode mode = (Mode) o;
        return Objects.equals(routes, mode.routes) && Objects.equals(mode, mode.mode) && selectedMode == mode.selectedMode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes, mode, selectedMode);
    }
    
}
    