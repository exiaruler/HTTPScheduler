package com.scheduler.app.backend.aREST.Models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.app.backend.aREST.Models.Base.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class Board extends ModelBase {
 
    // arduino board id
    @Column
    private String boardId;
    // board name
    @Column
    private String name;
    // ip address
    @Column
    private String ip;
    // active status
    @Column
    private boolean status=false;
    // scan device version
    @Column
    private long ScanDeviceVersion=0;
    // device list
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "board", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Device> device;
    

    public Board() {
    }

    public Board(String boardId, String name, String ip, boolean status, long ScanDeviceVersion, List<Device> device) {
        this.boardId = boardId;
        this.name = name;
        this.ip = ip;
        this.status = status;
        this.ScanDeviceVersion = ScanDeviceVersion;
        this.device = device;
    }

    public String getBoardId() {
        return this.boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
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

    public long getScanDeviceVersion() {
        return this.ScanDeviceVersion;
    }

    public void setScanDeviceVersion(long ScanDeviceVersion) {
        this.ScanDeviceVersion = ScanDeviceVersion;
    }

    public List<Device> getDevice() {
        return this.device;
    }

    public void setDevice(List<Device> device) {
        this.device = device;
    }

    public Board boardId(String boardId) {
        setBoardId(boardId);
        return this;
    }

    public Board name(String name) {
        setName(name);
        return this;
    }

    public Board ip(String ip) {
        setIp(ip);
        return this;
    }

    public Board status(boolean status) {
        setStatus(status);
        return this;
    }

    public Board ScanDeviceVersion(long ScanDeviceVersion) {
        setScanDeviceVersion(ScanDeviceVersion);
        return this;
    }

    public Board device(List<Device> device) {
        setDevice(device);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Board)) {
            return false;
        }
        Board board = (Board) o;
        return Objects.equals(boardId, board.boardId) && Objects.equals(name, board.name) && Objects.equals(ip, board.ip) && status == board.status && ScanDeviceVersion == board.ScanDeviceVersion && Objects.equals(device, board.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, name, ip, status, ScanDeviceVersion, device);
    }

    @Override
    public String toString() {
        return "{" +
            " boardId='" + getBoardId() + "'" +
            ", name='" + getName() + "'" +
            ", ip='" + getIp() + "'" +
            ", status='" + isStatus() + "'" +
            ", ScanDeviceVersion='" + getScanDeviceVersion() + "'" +
            ", device='" + getDevice() + "'" +
            "}";
    }


   
}
