package com.scheduler.app.backend.Models;
import com.scheduler.app.backend.Models.Base.*;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class Mode extends ModelBase{
    @ManyToOne(optional = false)
    private Routes routes;
    @Column
    private String mode;


    public Mode() {
    }


    public Mode(Routes routes, String mode) {
        this.routes = routes;
        this.mode = mode;
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


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mode)) {
            return false;
        }
        Mode mode = (Mode) o;
        return Objects.equals(routes, mode.routes) && Objects.equals(mode, mode.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes, mode);
    }
   
    
}
    