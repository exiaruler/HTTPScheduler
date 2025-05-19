package com.scheduler.app.backend.Messaging.Board.Models;
import java.util.Objects;

public class BoardRegister {
    
    private String boardId;
    private String password;

    public BoardRegister() {
    }

    public BoardRegister(String boardId, String password) {
        this.boardId = boardId;
        this.password = password;
    }

    public String getBoardId() {
        return this.boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BoardRegister boardId(String boardId) {
        setBoardId(boardId);
        return this;
    }

    public BoardRegister password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BoardRegister)) {
            return false;
        }
        BoardRegister boardRegister = (BoardRegister) o;
        return Objects.equals(boardId, boardRegister.boardId) && Objects.equals(password, boardRegister.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, password);
    }

    @Override
    public String toString() {
        return "{" +
            " boardId='" + getBoardId() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
    
}
