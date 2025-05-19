package com.scheduler.app.backend.aREST.Models;

import javax.persistence.*;

import com.scheduler.Base.ModelBase.ModelBase;
import java.util.Objects;
// Store versions of scanning device
// scan device layout 
// name,scanVersion,multipleDevice(0,1),deviceVariable,stateVariable,warningVariable,deviceStatus,getRoutesRequest,getRouteData
@Entity
public class ScanDevice extends ModelBase {
    
    @Column
    private String name;
    // version for scanning
    @Column
    private String scanVersion;
    // can scan for multiple devices
    @Column
    private boolean multipleDevice=false;
    // String variable to get device content
    @Column
    private String deviceVariable;
    // String variable for state
    @Column
    private String stateVariable;
    // String variable for warning
    @Column 
    private String warningVariable;
    // String variable for status
    @Column
    private String deviceStatus;
    // variable for routes http request
    @Column
    private String getRoutesRequest;
    // String variable to get route data
    @Column
    private String getRouteData;
    

    public ScanDevice() {
    }

    public ScanDevice(String name, String scanVersion, boolean multipleDevice, String deviceVariable, String stateVariable, String warningVariable, String deviceStatus, String getRoutesRequest, String getRouteData) {
        this.name = name;
        this.scanVersion = scanVersion;
        this.multipleDevice = multipleDevice;
        this.deviceVariable = deviceVariable;
        this.stateVariable = stateVariable;
        this.warningVariable = warningVariable;
        this.deviceStatus = deviceStatus;
        this.getRoutesRequest = getRoutesRequest;
        this.getRouteData = getRouteData;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScanVersion() {
        return this.scanVersion;
    }

    public void setScanVersion(String scanVersion) {
        this.scanVersion = scanVersion;
    }

    public boolean isMultipleDevice() {
        return this.multipleDevice;
    }

    public boolean getMultipleDevice() {
        return this.multipleDevice;
    }

    public void setMultipleDevice(boolean multipleDevice) {
        this.multipleDevice = multipleDevice;
    }

    public String getDeviceVariable() {
        return this.deviceVariable;
    }

    public void setDeviceVariable(String deviceVariable) {
        this.deviceVariable = deviceVariable;
    }

    public String getStateVariable() {
        return this.stateVariable;
    }

    public void setStateVariable(String stateVariable) {
        this.stateVariable = stateVariable;
    }

    public String getWarningVariable() {
        return this.warningVariable;
    }

    public void setWarningVariable(String warningVariable) {
        this.warningVariable = warningVariable;
    }

    public String getDeviceStatus() {
        return this.deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getGetRoutesRequest() {
        return this.getRoutesRequest;
    }

    public void setGetRoutesRequest(String getRoutesRequest) {
        this.getRoutesRequest = getRoutesRequest;
    }

    public String getGetRouteData() {
        return this.getRouteData;
    }

    public void setGetRouteData(String getRouteData) {
        this.getRouteData = getRouteData;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ScanDevice)) {
            return false;
        }
        ScanDevice scanDevice = (ScanDevice) o;
        return Objects.equals(name, scanDevice.name) && Objects.equals(scanVersion, scanDevice.scanVersion) && multipleDevice == scanDevice.multipleDevice && Objects.equals(deviceVariable, scanDevice.deviceVariable) && Objects.equals(stateVariable, scanDevice.stateVariable) && Objects.equals(warningVariable, scanDevice.warningVariable) && Objects.equals(deviceStatus, scanDevice.deviceStatus) && Objects.equals(getRoutesRequest, scanDevice.getRoutesRequest) && Objects.equals(getRouteData, scanDevice.getRouteData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scanVersion, multipleDevice, deviceVariable, stateVariable, warningVariable, deviceStatus, getRoutesRequest, getRouteData);
    }
    


}
