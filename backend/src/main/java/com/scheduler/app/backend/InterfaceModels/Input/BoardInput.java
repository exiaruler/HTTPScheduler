package com.scheduler.app.backend.InterfaceModels.Input;

import java.util.Objects;

import com.scheduler.app.backend.Hardware.Models.Hardware;

public class BoardInput {

    private String name="";
    private String password;
    private Hardware hardwareModel;


    public BoardInput() {
    }

    public BoardInput(String name, String password, Hardware hardwareModel) {
        this.name = name;
        this.password = password;
        this.hardwareModel = hardwareModel;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Hardware getHardwareModel() {
        return this.hardwareModel;
    }

    public void setHardwareModel(Hardware hardwareModel) {
        this.hardwareModel = hardwareModel;
    }

    public BoardInput name(String name) {
        setName(name);
        return this;
    }

    public BoardInput password(String password) {
        setPassword(password);
        return this;
    }

    public BoardInput hardwareModel(Hardware hardwareModel) {
        setHardwareModel(hardwareModel);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BoardInput)) {
            return false;
        }
        BoardInput boardInput = (BoardInput) o;
        return Objects.equals(name, boardInput.name) && Objects.equals(password, boardInput.password) && Objects.equals(hardwareModel, boardInput.hardwareModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, hardwareModel);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", password='" + getPassword() + "'" +
            ", hardwareModel='" + getHardwareModel() + "'" +
            "}";
    }
    
}
