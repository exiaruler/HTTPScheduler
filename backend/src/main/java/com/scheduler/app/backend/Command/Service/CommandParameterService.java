package com.scheduler.app.backend.Command.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.Command.Models.CommandParameter;
import com.scheduler.app.backend.Command.Repo.CommandParameterRepo;

@Service
public class CommandParameterService extends Base{
    public final CommandParameterRepo commandParameter;
    public CommandParameterService(CommandParameterRepo commandParameter) {
        this.commandParameter=commandParameter;
    }
    public HashMap<String,String> createComponentMap(){
        HashMap<String, String> comp = new HashMap<>();
        comp.put("pin","PinSelect");
        comp.put("output","OutputText");
        //comp.put("downput","OutputText");
        comp.put("rgbRed","OutputText");
        comp.put("rgbGreen","OutputText");
        comp.put("rgbBlue","OutputText");
        comp.put("deduction","NumberText");
        comp.put("runTarget","NumberText");
        comp.put("loops","NumberText");
        comp.put("interval","TimeControl");
        comp.put("beginDelay","TimeControl");
        comp.put("electo","ElectodeSelect");
        comp.put("startAngle","AngleInput");
        comp.put("moveAngle","AngleInput");
        comp.put("rgbSet","RgbPinsInput");
        comp.put("rgbType","RgbTypeSelect");
        return comp;
    }
    // electrode 
    public String electode[]={"anode","cathode"};

    public String rgbType[]={"COMMON_ANODE"};

    public CommandParameter getParameter(long id){
        return commandParameter.getReferenceById(id);
    }
    public CommandParameter getParameterInit(long id){
        return commandParameter.getParameter(id);
    }
    public List<CommandParameter> getParameterListInit(long id){
        return commandParameter.findParametersByCommand(id);
    }
    public CommandParameter saveParameter(CommandParameter rec){
        return commandParameter.save(rec);
    }

    

    
}
