package com.scheduler.app.backend.aREST.Models;

import javax.persistence.Entity;

import com.scheduler.app.backend.aREST.Models.Base.ModelBase;

@Entity
public class Section extends ModelBase {
    private String location;

    public Section() {
    }
    public Section(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Section other = (Section) obj;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        return true;
    }

    
}
