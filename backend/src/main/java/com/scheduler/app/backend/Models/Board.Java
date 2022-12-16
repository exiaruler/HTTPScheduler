package com.scheduler.app.backend.Models;
import javax.persistenance.*;
@Entity
public class Board{
    @id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column
    private int boardId;
    @Column
    private String name;
    @Column
    private long ip;
    @Column
    private boolean status;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBoardId() {
        return this.boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIp() {
        return this.ip;
    }

    public void setIp(long ip) {
        this.ip = ip;
    }

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}