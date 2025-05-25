package com.scheduler.app.backend.Messaging.Models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scheduler.Base.ModelBase.ModelBase;
// current output
@Entity
public class OutputCurrent extends ModelBase {
    
    @ManyToOne
    @JoinColumn(name="board_variable_id")
    @JsonBackReference
    private BoardVariable boardVariable;
    // current
    @Column
    private int current=0;
    // order of output
    @Column
    private int orderPosition;
    // if it's downput
    @Column
    private boolean downput;
    // if it's output
    @Column
    private boolean output;


    public OutputCurrent() {
    }

    public OutputCurrent(BoardVariable boardVariable, int current, int orderPosition, boolean downput, boolean output) {
        this.boardVariable = boardVariable;
        this.current = current;
        this.orderPosition = orderPosition;
        this.downput = downput;
        this.output = output;
    }

    public BoardVariable getBoardVariable() {
        return this.boardVariable;
    }

    public void setBoardVariable(BoardVariable boardVariable) {
        this.boardVariable = boardVariable;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getOrderPosition() {
        return this.orderPosition;
    }

    public void setOrderPosition(int orderPosition) {
        this.orderPosition = orderPosition;
    }

    public boolean isDownput() {
        return this.downput;
    }

    public boolean getDownput() {
        return this.downput;
    }

    public void setDownput(boolean downput) {
        this.downput = downput;
    }

    public boolean isOutput() {
        return this.output;
    }

    public boolean getOutput() {
        return this.output;
    }

    public void setOutput(boolean output) {
        this.output = output;
    }

    public OutputCurrent boardVariable(BoardVariable boardVariable) {
        setBoardVariable(boardVariable);
        return this;
    }

    public OutputCurrent current(int current) {
        setCurrent(current);
        return this;
    }

    public OutputCurrent orderPosition(int orderPosition) {
        setOrderPosition(orderPosition);
        return this;
    }

    public OutputCurrent downput(boolean downput) {
        setDownput(downput);
        return this;
    }

    public OutputCurrent output(boolean output) {
        setOutput(output);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OutputCurrent)) {
            return false;
        }
        OutputCurrent outputCurrent = (OutputCurrent) o;
        return Objects.equals(boardVariable, outputCurrent.boardVariable) && current == outputCurrent.current && orderPosition == outputCurrent.orderPosition && downput == outputCurrent.downput && output == outputCurrent.output;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardVariable, current, orderPosition, downput, output);
    }

    @Override
    public String toString() {
        return "{" +
            " boardVariable='" + getBoardVariable() + "'" +
            ", current='" + getCurrent() + "'" +
            ", orderPosition='" + getOrderPosition() + "'" +
            ", downput='" + isDownput() + "'" +
            ", output='" + isOutput() + "'" +
            "}";
    }
    
}
