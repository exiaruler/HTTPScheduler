package com.scheduler.app.backend.Command.Models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.Base.ModelBase.ModelBase;
import com.scheduler.app.backend.aREST.Models.Route;
// store route method and class origin
@Entity
@Table(indexes = @Index(columnList = "command,className"))
public class Command extends ModelBase{
    // type of command
    @Column
    private String commandType;
    // route or command name 
    @Column
    private String command;
    // class origin (legacy)
    @Column
    private String className;
    // does it have params
    @Column
    private boolean params=false;
    // total number params
    @Column
    private int totalParam=0;
    // has motor or servo
    @Column
    private boolean hasMotor=false;
    // route is animation (legacy)
    @Column
    private boolean animation=false;
    // command synchronous (legacy)
    @Column
    private boolean synchronous=false;
    // list of routes that use command
    //@JsonManagedReference("command-route")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "command", cascade =CascadeType.ALL)
    private List<Route> commandUsedRoutes;
    // list of parameters used for this command
    @JsonManagedReference("command-commandParameter")
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "command", cascade =CascadeType.ALL)
    private List<CommandParameter> commandParameter;
    

    public Command() {
    }

    public Command(String commandType, String command, String className, boolean params, int totalParam, boolean hasMotor, boolean animation, boolean synchronous, List<Route> commandUsedRoutes, List<CommandParameter> commandParameter) {
        this.commandType = commandType;
        this.command = command;
        this.className = className;
        this.params = params;
        this.totalParam = totalParam;
        this.hasMotor = hasMotor;
        this.animation = animation;
        this.synchronous = synchronous;
        this.commandUsedRoutes = commandUsedRoutes;
        this.commandParameter = commandParameter;
    }

    public String getCommandType() {
        return this.commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isParams() {
        return this.params;
    }

    public boolean getParams() {
        return this.params;
    }

    public void setParams(boolean params) {
        this.params = params;
    }

    public int getTotalParam() {
        return this.totalParam;
    }

    public void setTotalParam(int totalParam) {
        this.totalParam = totalParam;
    }

    public boolean isHasMotor() {
        return this.hasMotor;
    }

    public boolean getHasMotor() {
        return this.hasMotor;
    }

    public void setHasMotor(boolean hasMotor) {
        this.hasMotor = hasMotor;
    }

    public boolean isAnimation() {
        return this.animation;
    }

    public boolean getAnimation() {
        return this.animation;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
    }

    public boolean isSynchronous() {
        return this.synchronous;
    }

    public boolean getSynchronous() {
        return this.synchronous;
    }

    public void setSynchronous(boolean synchronous) {
        this.synchronous = synchronous;
    }

    public List<Route> getCommandUsedRoutes() {
        return this.commandUsedRoutes;
    }

    public void setCommandUsedRoutes(List<Route> commandUsedRoutes) {
        this.commandUsedRoutes = commandUsedRoutes;
    }

    public List<CommandParameter> getCommandParameter() {
        return this.commandParameter;
    }

    public void setCommandParameter(List<CommandParameter> commandParameter) {
        this.commandParameter = commandParameter;
    }

    public Command commandType(String commandType) {
        setCommandType(commandType);
        return this;
    }

    public Command command(String command) {
        setCommand(command);
        return this;
    }

    public Command className(String className) {
        setClassName(className);
        return this;
    }

    public Command params(boolean params) {
        setParams(params);
        return this;
    }

    public Command totalParam(int totalParam) {
        setTotalParam(totalParam);
        return this;
    }

    public Command hasMotor(boolean hasMotor) {
        setHasMotor(hasMotor);
        return this;
    }

    public Command animation(boolean animation) {
        setAnimation(animation);
        return this;
    }

    public Command synchronous(boolean synchronous) {
        setSynchronous(synchronous);
        return this;
    }

    public Command commandUsedRoutes(List<Route> commandUsedRoutes) {
        setCommandUsedRoutes(commandUsedRoutes);
        return this;
    }

    public Command commandParameter(List<CommandParameter> commandParameter) {
        setCommandParameter(commandParameter);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Command)) {
            return false;
        }
        Command command = (Command) o;
        return Objects.equals(commandType, command.commandType) && Objects.equals(command, command.command) && Objects.equals(className, command.className) && params == command.params && totalParam == command.totalParam && hasMotor == command.hasMotor && animation == command.animation && synchronous == command.synchronous && Objects.equals(commandUsedRoutes, command.commandUsedRoutes) && Objects.equals(commandParameter, command.commandParameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandType, command, className, params, totalParam, hasMotor, animation, synchronous, commandUsedRoutes, commandParameter);
    }

    @Override
    public String toString() {
        return "{" +
            " commandType='" + getCommandType() + "'" +
            ", command='" + getCommand() + "'" +
            ", className='" + getClassName() + "'" +
            ", params='" + isParams() + "'" +
            ", totalParam='" + getTotalParam() + "'" +
            ", hasMotor='" + isHasMotor() + "'" +
            ", animation='" + isAnimation() + "'" +
            ", synchronous='" + isSynchronous() + "'" +
            ", commandUsedRoutes='" + getCommandUsedRoutes() + "'" +
            ", commandParameter='" + getCommandParameter() + "'" +
            "}";
    }
    
    
}