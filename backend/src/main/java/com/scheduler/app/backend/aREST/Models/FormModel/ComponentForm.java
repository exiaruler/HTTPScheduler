package com.scheduler.app.backend.aREST.Models.FormModel;

import com.scheduler.Base.FormBase;
import com.scheduler.app.backend.aREST.Models.Component;

import java.util.Objects;

public class ComponentForm extends FormBase {

    public ComponentForm() {
        this.setModel(new Component());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ComponentForm)) {
            return false;
        }
        ComponentForm componentForm = (ComponentForm) o;
        return Objects.equals(this, componentForm);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
            "}";
    }
    
}
