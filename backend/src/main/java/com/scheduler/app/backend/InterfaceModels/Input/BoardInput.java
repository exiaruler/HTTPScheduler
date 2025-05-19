package com.scheduler.app.backend.InterfaceModels.Input;

import java.util.Objects;

import javax.persistence.Embedded;

import com.scheduler.app.backend.Hardware.Models.Hardware;

public class BoardInput {

    private String boardName="";
    //@Embeddable
    @Embedded
    private Hardware hardwareModel;

    public BoardInput() {
    }

    public BoardInput(String boardName, Hardware hardwareModel) {
        this.boardName = boardName;
        this.hardwareModel = hardwareModel;
    }

    public String getBoardName() {
        return this.boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Hardware getHardwareModel() {
        return this.hardwareModel;
    }

    public void setHardwareModel(Hardware hardwareModel) {
        this.hardwareModel = hardwareModel;
    }

    public BoardInput boardName(String boardName) {
        setBoardName(boardName);
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
        return Objects.equals(boardName, boardInput.boardName) && Objects.equals(hardwareModel, boardInput.hardwareModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardName, hardwareModel);
    }

    @Override
    public String toString() {
        return "{" +
            " boardName='" + getBoardName() + "'" +
            ", hardwareModel='" + getHardwareModel() + "'" +
            "}";
    }
    
}
