package com.scheduler.app.backend.Messaging.Models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scheduler.Base.ModelBase.ModelBase;
// rgb pins
@Entity
@Table(name="Board_pin")
public class BoardPin extends ModelBase {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="board_task_id")
    @JsonBackReference
    private BoardTask boardTask;
    @Column
    private int pin;


    public BoardPin() {
    }

    public BoardPin(BoardTask boardTask, int pin) {
        this.boardTask = boardTask;
        this.pin = pin;
    }

    public BoardTask getBoardTask() {
        return this.boardTask;
    }

    public void setBoardTask(BoardTask boardTask) {
        this.boardTask = boardTask;
    }

    public int getPin() {
        return this.pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public BoardPin boardTask(BoardTask boardTask) {
        setBoardTask(boardTask);
        return this;
    }

    public BoardPin pin(int pin) {
        setPin(pin);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BoardPin)) {
            return false;
        }
        BoardPin boardPin = (BoardPin) o;
        return Objects.equals(boardTask, boardPin.boardTask) && pin == boardPin.pin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardTask, pin);
    }

    @Override
    public String toString() {
        return "{" +
            " boardTask='" + getBoardTask() + "'" +
            ", pin='" + getPin() + "'" +
            "}";
    }
    
    
}
