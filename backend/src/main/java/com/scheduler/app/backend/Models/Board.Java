package com.scheduler.app.backend.Models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.app.backend.Models.Base.*;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(indexes = @Index(columnList = "onboard_Id"))
public class Board extends ModelBase {
 
    //@NotBlank(message = "Name must not be blank")
    @Column(name = "onboard_id")
    private int onboardId;
    @Column
    private String name;
    @Column
    private String ip;
    @Column
    private boolean status=false;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<Devices> device;


    public Board() {
    }
    
    public Board(int boardId, String name, String ip, boolean status, List<Devices> device) {
        this.onboardId = boardId;
        this.name = name;
        this.ip = ip;
        this.status = status;
        this.device = device;
    }
    
    
    public List<Devices> getDevice() {
        return this.device;
    }

    public void setDevice(List<Devices> device) {
        this.device = device;
    }
    
    public int getBoardId() {
        return this.onboardId;
    }

    public void setBoardId(int boardId) {
        this.onboardId = boardId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
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


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Board)) {
            return false;
        }
        Board board = (Board) o;
        return onboardId == board.onboardId && Objects.equals(name, board.name) && Objects.equals(ip, board.ip) && status == board.status && Objects.equals(device, board.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(onboardId, name, ip, status, device);
    }


}
