package com.scheduler.app.backend.Command.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
import com.scheduler.app.backend.Command.Models.Command;
import com.scheduler.app.backend.Command.Service.CommandService;
@RestController
@RequestMapping(value = "/command")
public class CommandController extends ControllerBase{
    @Autowired
    private CommandService commandService;
    public CommandController(){
        this.objectClass=this.pathBase+".Command.Models.Command";
    }
    @GetMapping("/get-commands")
    public List<Command> getCommands() {
        return commandService.getCommands();
    }
}
