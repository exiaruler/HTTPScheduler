package com.scheduler.app.backend.aREST.Models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scheduler.app.backend.aREST.Models.Base.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(indexes = @Index(columnList = "onboard_Id"),name="board")
public class Board extends ModelBase {
 
    //@NotBlank(message = "Name must not be blank")
    // arduino board id
    @Column(name = "onboard_id")
    private int onboardId=0;
    // board name
    @Column
    private String name;
    @Column
    private String ip;
    // active status
    @Column
    private boolean status=false;
    @Column
    private long ScanDeviceVersion=0;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    private Set<Devices> device;


    public Board() {
    }


    public Board(int onboardId, String name, String ip, boolean status, long ScanDeviceVersion, Set<Devices> device) {
        this.onboardId = onboardId;
        this.name = name;
        this.ip = ip;
        this.status = status;
        this.ScanDeviceVersion = ScanDeviceVersion;
        this.device = device;
    }
    

    public int getOnboardId() {
        return this.onboardId;
    }

    public void setOnboardId(int onboardId) {
        this.onboardId = onboardId;
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

    public Set<Devices> getDevice() {
        return this.device;
    }

    public void setDevice(Set<Devices> device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Board)) {
            return false;
        }
        Board board = (Board) o;
        return onboardId == board.onboardId && Objects.equals(name, board.name) && Objects.equals(ip, board.ip) && status == board.status && ScanDeviceVersion == board.ScanDeviceVersion && Objects.equals(device, board.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(onboardId, name, ip, status, ScanDeviceVersion, device);
    }
   

}
