package com.scheduler.app.backend.aREST.Models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.app.backend.aREST.Models.Base.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(indexes = @Index(columnList = "boardId"))
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
    // arestFramework installed
    @Column boolean arest=true;
    // scan device version
    @Column
    private long ScanDeviceVersion=0;
    // device list
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "board", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Device> device;
    
    // section that the board belongs to
    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="section_id")
    private Section section;


    public Board() {
    }

    public Board(String boardId, String name, String ip, boolean status, boolean arest, long ScanDeviceVersion, List<Device> device, Section section) {
        this.boardId = boardId;
        this.name = name;
        this.ip = ip;
        this.status = status;
        this.arest = arest;
        this.ScanDeviceVersion = ScanDeviceVersion;
        this.device = device;
        this.section = section;
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

    public boolean isArest() {
        return this.arest;
    }

    public boolean getArest() {
        return this.arest;
    }

    public void setArest(boolean arest) {
        this.arest = arest;
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

    public Section getSection() {
        return this.section;
    }

    public void setSection(Section section) {
        this.section = section;
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

    public Board arest(boolean arest) {
        setArest(arest);
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

    public Board section(Section section) {
        setSection(section);
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
        return Objects.equals(boardId, board.boardId) && Objects.equals(name, board.name) && Objects.equals(ip, board.ip) && status == board.status && arest == board.arest && ScanDeviceVersion == board.ScanDeviceVersion && Objects.equals(device, board.device) && Objects.equals(section, board.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, name, ip, status, arest, ScanDeviceVersion, device, section);
    }

    @Override
    public String toString() {
        return "{" +
            " boardId='" + getBoardId() + "'" +
            ", name='" + getName() + "'" +
            ", ip='" + getIp() + "'" +
            ", status='" + isStatus() + "'" +
            ", arest='" + isArest() + "'" +
            ", ScanDeviceVersion='" + getScanDeviceVersion() + "'" +
            ", device='" + getDevice() + "'" +
            ", section='" + getSection() + "'" +
            "}";
    }
    
   
}
