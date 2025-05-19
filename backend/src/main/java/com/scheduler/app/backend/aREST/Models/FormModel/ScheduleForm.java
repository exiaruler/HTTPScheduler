package com.scheduler.app.backend.aREST.Models.FormModel;

import java.util.Objects;

import com.scheduler.Base.FormBase;
import com.scheduler.app.backend.InterfaceModels.Input.ScheduleInput;

public class ScheduleForm extends FormBase{
 
    public ScheduleForm() {
        // variable not to be used
        this.addNotUseKey("mode");
        this.setModel(new ScheduleInput());
        this.addCustomInput("deviceId","dropdown");
        this.addCustomInput("routeId", "dropdown");
        this.addCustomInput("modeId","dropdown");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ScheduleForm)) {
            return false;
        }
        ScheduleForm scheduleForm = (ScheduleForm) o;
        return Objects.equals(this, scheduleForm);
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
