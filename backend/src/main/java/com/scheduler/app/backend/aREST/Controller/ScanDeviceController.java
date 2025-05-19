package com.scheduler.app.backend.aREST.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
import com.scheduler.app.backend.aREST.Models.ScanDevice;
import com.scheduler.app.backend.aREST.Service.ScanDeviceService;

@RestController
@RequestMapping(value = "/scan-device")
public class ScanDeviceController extends ControllerBase {
    @Autowired
    private ScanDeviceService service;
    @PostMapping(value="/addtest")
    public ScanDevice testScanAdd(@RequestBody String string){
        return service.addNewScanFromString(string);
    }
    @PostMapping(value="/test")
    public Object test(@RequestBody Object test){
        return test;

    }
    
}
