package com.scheduler.app.backend.aREST.Models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scheduler.Base.ModelBase.ModelBase;

@Entity
public class PinsParameter extends ModelBase {
    // route
    @JsonBackReference("route-pins")
    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;
    // pin
    @Column
    private int pin;
    // key associated to background task
    @Column
    private String key="";
    // pin order
    @Column
    private int pinOrder;


    public PinsParameter() {
    }

    public PinsParameter(Route route, int pin, String key, int pinOrder) {
        this.route = route;
        this.pin = pin;
        this.key = key;
        this.pinOrder = pinOrder;
    }

    public Route getRoute() {
        return this.route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getPin() {
        return this.pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPinOrder() {
        return this.pinOrder;
    }

    public void setPinOrder(int pinOrder) {
        this.pinOrder = pinOrder;
    }

    public PinsParameter route(Route route) {
        setRoute(route);
        return this;
    }

    public PinsParameter pin(int pin) {
        setPin(pin);
        return this;
    }

    public PinsParameter key(String key) {
        setKey(key);
        return this;
    }

    public PinsParameter pinOrder(int pinOrder) {
        setPinOrder(pinOrder);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PinsParameter)) {
            return false;
        }
        PinsParameter pinsParameter = (PinsParameter) o;
        return Objects.equals(route, pinsParameter.route) && pin == pinsParameter.pin && Objects.equals(key, pinsParameter.key) && pinOrder == pinsParameter.pinOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(route, pin, key, pinOrder);
    }

    @Override
    public String toString() {
        return "{" +
            " route='" + getRoute() + "'" +
            ", pin='" + getPin() + "'" +
            ", key='" + getKey() + "'" +
            ", pinOrder='" + getPinOrder() + "'" +
            "}";
    }

    
}
