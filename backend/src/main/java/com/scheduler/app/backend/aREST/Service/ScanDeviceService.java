package com.scheduler.app.backend.aREST.Service;
import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.aREST.Models.ScanDevice;
import com.scheduler.app.backend.aREST.Repo.ScanDeviceRepo;
@Service
public class ScanDeviceService extends Base {
    private final ScanDeviceRepo service;

    public ScanDeviceService(ScanDeviceRepo service) {
        this.service = service;
    }

    public ScanDevice addScan(ScanDevice scan){
        return service.save(scan);
    }
    
    public ScanDevice addNewScanFromString(String scan){
        String [] scanArr=scan.split(",");
        //ScanDevice checkExist=getScan(scanArr[1]);
        //if(checkExist!=null){
            //return checkExist;
        //}
        ScanDevice newScan=new ScanDevice();
        newScan.setName(scanArr[0]);
        newScan.setScanVersion(scanArr[1]);
        if(scanArr[2].equals("1")){
            newScan.setMultipleDevice(true);
        }
        newScan.setDeviceVariable(scanArr[3]);
        newScan.setStateVariable(scanArr[4]);
        newScan.setWarningVariable(scanArr[5]);
        newScan.setDeviceStatus(scanArr[6]);
        newScan.setGetRoutesRequest(scanArr[7]);
        newScan.setGetRouteData(scanArr[8]);
        ScanDevice save=addScan(newScan);
        return save;
    }
    
    public ScanDevice getScan(String code){
        ScanDevice scan=null;
        long t=service.getIdByCode(code);
        scan=service.findById(t).get();
        return scan;
    }
    
}
