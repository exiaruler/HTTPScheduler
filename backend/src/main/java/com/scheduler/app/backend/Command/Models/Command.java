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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.Base.ModelBase.ModelBase;
import com.scheduler.app.backend.aREST.Models.Route;
// store route method and class origin
@Entity
@Table(indexes = @Index(columnList = "command,className"))
public class Command extends ModelBase{
    // route name
    @Column
    private String command;
    // class origin
    @Column
    private String className;
    @Column
    private boolean params=false;
    // total number params
    @Column
    private int totalParam=0;
    // has motor or servo
    @Column
    private boolean hasMotor=false;
    // route is animation
    @Column
    private boolean animation=false;
    // command synchronous
    @Column
    private boolean synchronous=false;
    // list of routes that use command
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "command", cascade ={ CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH })
    private List<Route> commandUsedRoutes;
    

    public Command() {
    }

    public Command(String command, String className, boolean params, int totalParam, boolean hasMotor, boolean animation, boolean synchronous, List<Route> commandUsedRoutes) {
        this.command = command;
        this.className = className;
        this.params = params;
        this.totalParam = totalParam;
        this.hasMotor = hasMotor;
        this.animation = animation;
        this.synchronous = synchronous;
        this.commandUsedRoutes = commandUsedRoutes;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Command)) {
            return false;
        }
        Command command = (Command) o;
        return Objects.equals(command, command.command) && Objects.equals(className, command.className) && params == command.params && totalParam == command.totalParam && hasMotor == command.hasMotor && animation == command.animation && synchronous == command.synchronous && Objects.equals(commandUsedRoutes, command.commandUsedRoutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, className, params, totalParam, hasMotor, animation, synchronous, commandUsedRoutes);
    }

    @Override
    public String toString() {
        return "{" +
            " command='" + getCommand() + "'" +
            ", className='" + getClassName() + "'" +
            ", params='" + isParams() + "'" +
            ", totalParam='" + getTotalParam() + "'" +
            ", hasMotor='" + isHasMotor() + "'" +
            ", animation='" + isAnimation() + "'" +
            ", synchronous='" + isSynchronous() + "'" +
            ", commandUsedRoutes='" + getCommandUsedRoutes() + "'" +
            "}";
    }

    
    
}