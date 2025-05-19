package com.scheduler.app.backend.Messaging.Board.Models;
import java.util.Objects;
// board message after executed commands
public class BoardInput {
    // message type
    private String messageType;
    // command exection sucess
    private boolean success;
    // data sent back
    private String data;
    // position of task in array
    private int taskPosition;
    // RAM space left on board
    private int ramSpace;
    // milliseconds took to process message
    private int responseTime;
    public BoardInput() {
    }

    public BoardInput(String messageType, boolean success, String data, int taskPosition, int ramSpace) {
        this.messageType = messageType;
        this.success = success;
        this.data = data;
        this.taskPosition = taskPosition;
        this.ramSpace = ramSpace;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTaskPosition() {
        return this.taskPosition;
    }

    public void setTaskPosition(int taskPosition) {
        this.taskPosition = taskPosition;
    }

    public int getRamSpace() {
        return this.ramSpace;
    }

    public void setRamSpace(int ramSpace) {
        this.ramSpace = ramSpace;
    }

    public BoardInput messageType(String messageType) {
        setMessageType(messageType);
        return this;
    }

    public BoardInput success(boolean success) {
        setSuccess(success);
        return this;
    }

    public BoardInput data(String data) {
        setData(data);
        return this;
    }

    public BoardInput taskPosition(int taskPosition) {
        setTaskPosition(taskPosition);
        return this;
    }

    public BoardInput ramSpace(int ramSpace) {
        setRamSpace(ramSpace);
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
        return Objects.equals(messageType, boardInput.messageType) && success == boardInput.success && Objects.equals(data, boardInput.data) && taskPosition == boardInput.taskPosition && ramSpace == boardInput.ramSpace;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageType, success, data, taskPosition, ramSpace);
    }

    @Override
    public String toString() {
        return "{" +
            " messageType='" + getMessageType() + "'" +
            ", success='" + isSuccess() + "'" +
            ", data='" + getData() + "'" +
            ", taskPosition='" + getTaskPosition() + "'" +
            ", ramSpace='" + getRamSpace() + "'" +
            "}";
    }
    
}
