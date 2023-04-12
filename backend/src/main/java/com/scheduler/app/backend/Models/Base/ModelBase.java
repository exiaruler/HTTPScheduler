package com.scheduler.app.backend.Models.Base;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.ZoneId;
// Base class for models 
@MappedSuperclass
public class ModelBase{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private LocalDateTime createdDate=LocalDateTime.now(ZoneId.of("Australia/Sydney"));


    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}