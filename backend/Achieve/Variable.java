/* 
package com.scheduler.app.backend.aREST.Models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.scheduler.Base.ModelBase.ModelBase;
import java.util.Objects;
// store values of custom device that is not align with framework standards or custom
@Entity
public class Variable extends ModelBase {
 
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="device_id")
    private Device device;
    // json key
    @Column
    private String key;
    // json value
    @Column
    private String value;
   
}
*/