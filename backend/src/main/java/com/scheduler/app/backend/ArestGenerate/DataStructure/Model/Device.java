package com.scheduler.app.backend.ArestGenerate.DataStructure.Model;

import java.util.ArrayList;
import java.util.Objects;

public class Device {
    private String name="";
    private String state="";
    private String warning="";
    private String routes="";
    private String status="";
    private ArrayList<Component> component=new ArrayList<Component>(10);
    private String type="";
    private String subType="";
    private ArrayList<Route> route=new ArrayList<Route>(10);

    public Device() {
    }

    public Device(String name, String state, String warning, String routes, String status, ArrayList<Component> component, String type, String subType, ArrayList<Route> route) {
        this.name = name;
        this.state = state;
        this.warning = warning;
        this.routes = routes;
        this.status = status;
        this.component = component;
        this.type = type;
        this.subType = subType;
        this.route = route;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWarning() {
        return this.warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getRoutes() {
        return this.routes;
    }

    public void setRoutes(String routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Component> getComponent() {
        return this.component;
    }

    public void setComponent(ArrayList<Component> component) {
        this.component = component;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return this.subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public ArrayList<Route> getRoute() {
        return this.route;
    }

    public void setRoute(ArrayList<Route> route) {
        this.route = route;
    }

    public Device name(String name) {
        setName(name);
        return this;
    }

    public Device state(String state) {
        setState(state);
        return this;
    }

    public Device warning(String warning) {
        setWarning(warning);
        return this;
    }

    public Device routes(String routes) {
        setRoutes(routes);
        return this;
    }

    public Device status(String status) {
        setStatus(status);
        return this;
    }

    public Device component(ArrayList<Component> component) {
        setComponent(component);
        return this;
    }

    public Device type(String type) {
        setType(type);
        return this;
    }

    public Device subType(String subType) {
        setSubType(subType);
        return this;
    }

    public Device route(ArrayList<Route> route) {
        setRoute(route);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Device)) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(name, device.name) && Objects.equals(state, device.state) && Objects.equals(warning, device.warning) && Objects.equals(routes, device.routes) && Objects.equals(status, device.status) && Objects.equals(component, device.component) && Objects.equals(type, device.type) && Objects.equals(subType, device.subType) && Objects.equals(route, device.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state, warning, routes, status, component, type, subType, route);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", state='" + getState() + "'" +
            ", warning='" + getWarning() + "'" +
            ", routes='" + getRoutes() + "'" +
            ", status='" + getStatus() + "'" +
            ", component='" + getComponent() + "'" +
            ", type='" + getType() + "'" +
            ", subType='" + getSubType() + "'" +
            ", route='" + getRoute() + "'" +
            "}";
    }
    
}
