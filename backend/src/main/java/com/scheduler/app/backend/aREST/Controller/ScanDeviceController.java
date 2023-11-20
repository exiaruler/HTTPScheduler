package com.scheduler.app.backend.aREST.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.app.backend.aREST.Models.ScanDevice;
import com.scheduler.app.backend.aREST.Service.ScanDeviceService;

@RestController
public class ScanDeviceController {
    @Autowired
    private ScanDeviceService service;
    @PostMapping(value="/scan-device/addtest")
    public ScanDevice testScanAdd(@RequestBody String string){
        return service.addNewScanFromString(string);
    }
    @PostMapping(value="/scan-device/test")
    public Object test(@RequestBody Object test){
        return test;

    }
    
}
