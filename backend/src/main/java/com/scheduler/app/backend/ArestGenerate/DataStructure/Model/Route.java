package com.scheduler.app.backend.ArestGenerate.DataStructure.Model;

import java.util.ArrayList;
import java.util.Objects;

public class Route {
    private String routeName;
    private boolean modes=false;
    private ArrayList<String> params=new ArrayList<String>(15);
    private boolean commandAction=false;


    public Route() {
    }

    public Route(String routeName, boolean modes, ArrayList<String> params, boolean commandAction) {
        this.routeName = routeName;
        this.modes = modes;
        this.params = params;
        this.commandAction = commandAction;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public boolean isModes() {
        return this.modes;
    }

    public boolean getModes() {
        return this.modes;
    }

    public void setModes(boolean modes) {
        this.modes = modes;
    }

    public ArrayList<String> getParams() {
        return this.params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    public boolean isCommandAction() {
        return this.commandAction;
    }

    public boolean getCommandAction() {
        return this.commandAction;
    }

    public void setCommandAction(boolean commandAction) {
        this.commandAction = commandAction;
    }

    public Route routeName(String routeName) {
        setRouteName(routeName);
        return this;
    }

    public Route modes(boolean modes) {
        setModes(modes);
        return this;
    }

    public Route params(ArrayList<String> params) {
        setParams(params);
        return this;
    }

    public Route commandAction(boolean commandAction) {
        setCommandAction(commandAction);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Route)) {
            return false;
        }
        Route route = (Route) o;
        return Objects.equals(routeName, route.routeName) && modes == route.modes && Objects.equals(params, route.params) && commandAction == route.commandAction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeName, modes, params, commandAction);
    }

    @Override
    public String toString() {
        return "{" +
            " routeName='" + getRouteName() + "'" +
            ", modes='" + isModes() + "'" +
            ", params='" + getParams() + "'" +
            ", commandAction='" + isCommandAction() + "'" +
            "}";
    }
    
    
}
