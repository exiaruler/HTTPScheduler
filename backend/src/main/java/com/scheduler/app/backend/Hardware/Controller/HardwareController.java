package com.scheduler.app.backend.Hardware.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
import com.scheduler.app.backend.Hardware.Models.Hardware;
import com.scheduler.app.backend.Hardware.Service.HardwareService;

@RestController
@RequestMapping(value = "/hardware")
public class HardwareController extends ControllerBase{
    @Autowired
    private HardwareService hardware;

    @GetMapping(value="/get-hardwares")
    public List<Hardware> getHardwares(){
        return hardware.getBoards();
    }
}
