package com.scheduler.app.backend.aREST.Models;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.scheduler.app.backend.aREST.Models.Base.CustomBase.TaskBase;

import java.time.*;
import java.util.Objects;
// stores schedule task by day and time
@Entity
public class ScheduledTask extends TaskBase {
    // daily run
    private boolean daily=false;
    // how often it should 
    private double frequency=1.0;
    // time parameter to run
    private LocalTime time;
    // days to run
    
    
 
    
    
}
