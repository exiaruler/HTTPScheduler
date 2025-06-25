package com.scheduler.app.backend.Command.Models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scheduler.Base.ModelBase.ModelBase;

@Entity
public class CommandParameter extends ModelBase{

    // link to command
    @JsonBackReference("command-commandParameter")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="command_id")
    private Command command;

    // order of command (legacy)
    @Column
    private int parameterOrder;
    // component
    @Column 
    private String component;
    // component label
    @Column 
    private String label="";
    // input type
    @Column
    private String type;
    // parameter a pin
    @Column
    private boolean pin=false;
    // key of background task
    @Column
    private String backgroundKey;
    // sub key of background task
    @Column 
    private String subKey="";
    // class of key that it belongs to
    @Column
    private String className;



    public CommandParameter() {
    }

    public CommandParameter(Command command, int parameterOrder, String component, String label, String type, boolean pin, String backgroundKey, String subKey, String className) {
        this.command = command;
        this.parameterOrder = parameterOrder;
        this.component = component;
        this.label = label;
        this.type = type;
        this.pin = pin;
        this.backgroundKey = backgroundKey;
        this.subKey = subKey;
        this.className = className;
    }

    public Command getCommand() {
        return this.command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public int getParameterOrder() {
        return this.parameterOrder;
    }

    public void setParameterOrder(int parameterOrder) {
        this.parameterOrder = parameterOrder;
    }

    public String getComponent() {
        return this.component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPin() {
        return this.pin;
    }

    public boolean getPin() {
        return this.pin;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
    }

    public String getBackgroundKey() {
        return this.backgroundKey;
    }

    public void setBackgroundKey(String backgroundKey) {
        this.backgroundKey = backgroundKey;
    }

    public String getSubKey() {
        return this.subKey;
    }

    public void setSubKey(String subKey) {
        this.subKey = subKey;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CommandParameter command(Command command) {
        setCommand(command);
        return this;
    }

    public CommandParameter parameterOrder(int parameterOrder) {
        setParameterOrder(parameterOrder);
        return this;
    }

    public CommandParameter component(String component) {
        setComponent(component);
        return this;
    }

    public CommandParameter label(String label) {
        setLabel(label);
        return this;
    }

    public CommandParameter type(String type) {
        setType(type);
        return this;
    }

    public CommandParameter pin(boolean pin) {
        setPin(pin);
        return this;
    }

    public CommandParameter backgroundKey(String backgroundKey) {
        setBackgroundKey(backgroundKey);
        return this;
    }

    public CommandParameter subKey(String subKey) {
        setSubKey(subKey);
        return this;
    }

    public CommandParameter className(String className) {
        setClassName(className);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CommandParameter)) {
            return false;
        }
        CommandParameter commandParameter = (CommandParameter) o;
        return Objects.equals(command, commandParameter.command) && parameterOrder == commandParameter.parameterOrder && Objects.equals(component, commandParameter.component) && Objects.equals(label, commandParameter.label) && Objects.equals(type, commandParameter.type) && pin == commandParameter.pin && Objects.equals(backgroundKey, commandParameter.backgroundKey) && Objects.equals(subKey, commandParameter.subKey) && Objects.equals(className, commandParameter.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, parameterOrder, component, label, type, pin, backgroundKey, subKey, className);
    }

    @Override
    public String toString() {
        return "{" +
            " command='" + getCommand() + "'" +
            ", parameterOrder='" + getParameterOrder() + "'" +
            ", component='" + getComponent() + "'" +
            ", label='" + getLabel() + "'" +
            ", type='" + getType() + "'" +
            ", pin='" + isPin() + "'" +
            ", backgroundKey='" + getBackgroundKey() + "'" +
            ", subKey='" + getSubKey() + "'" +
            ", className='" + getClassName() + "'" +
            "}";
    }
    
    
    
}
