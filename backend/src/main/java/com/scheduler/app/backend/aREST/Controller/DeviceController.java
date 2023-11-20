package com.scheduler.app.backend.aREST.Controller;
import com.scheduler.app.backend.aREST.Models.Devices;
import com.scheduler.app.backend.aREST.Service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {
    @Autowired
    private DeviceService service;
    @PostMapping(value="/device/adddevice")
    public Devices addDevice(@RequestBody Devices entry){
        return service.addDevice(entry);

    }
    @GetMapping(value="/device/getalldevices")
    public List<Devices> getAllDevices(){
        return service.getAllDevice();
    }
    @GetMapping(value="/device/get-device/{id}")
    public Devices getDevice(@PathVariable long id){
        return service.getDevice(id);
    }
    
}
