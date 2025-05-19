package com.scheduler.app.backend.Command.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.Command.CommandFunction;
import com.scheduler.app.backend.Command.Models.Command;
import com.scheduler.app.backend.Command.Models.CommandParameter;
import com.scheduler.app.backend.Command.Repo.CommandRepo;
import com.scheduler.app.backend.Hardware.Service.HardwareService;

@Configuration
@Service
public class CommandService extends Base {
    public final CommandRepo command;
    public CommandFunction function=new CommandFunction();
    public final CommandParameterService commandParaService;
    public final HardwareService hardwareService;
    private final CommandFunction commandArr[]={
        new CommandFunction(new Command("action","setLed", "", true, 3, false, false, false, null,null), new String[]{"electo","pin","output"}),
        //new CommandFunction(new Command("background","ledblink", "", true, 4, true, false, false, null, null), new String[] {"pin","interval","number","number","number","number"}),
        new CommandFunction(new Command("action","setRgb", "", true, 5, false, false, false, null,null),new String[]{"rgbType","rgbSet","rgbRed","rgbGreen","rgbBlue"}),
        //new CommandFunction(new Command("action","clearQueue", "", false, 0, false, false, false, null, null),new String[]{}),
        //new CommandFunction(new Command("background","ledfade","",true,5,false,false, true, null, null),new String[]{"pin","number","number","time","number"}),
        new CommandFunction(new Command("action","setServo", "", true, 6, true, false, false, null, null),new String[]{"pin","startAngle","moveAngle","time","beginDelay","interval"})   
    };
    
    //public TaskService taskService;
    public CommandService(CommandRepo command,CommandParameterService commandParaService,HardwareService hardwareService) {
        this.command = command;
        this.commandParaService=commandParaService;
        this.hardwareService=hardwareService;
    }
    public Command getCommand(long id){
        return command.getReferenceById(id);
    }
    @Bean(initMethod="init")
    public void initData(){
    
        for(int i=0; i<commandArr.length; i++){
            Command commandItem=commandArr[i].command;
            String [] para=commandArr[i].commandParameters;
            Command exist=command.findCommand(commandItem.getCommandType(), commandItem.getCommand());
            if(exist!=null){
                exist.setCommandType(commandItem.getCommandType());
                exist.setCommand(commandItem.getCommand());
                exist.setHasMotor(commandItem.getHasMotor());
                exist.setParams(commandItem.getParams());
                exist.setTotalParam(commandItem.getTotalParam());
                //exist.setCommandParameter(createParameterSet(para, exist));
                command.save(exist);
            }else
            {
                commandItem.setCommandParameter(createParameterSet(para, commandItem));
                command.save(commandItem);
            }
        }
        hardwareService.initData();
            
    }
    public List<CommandParameter> createParameterSet(String [] parameters,Command command){
        HashMap<String,String> components=commandParaService.createComponentMap();
        List<CommandParameter> commandParaSet=new ArrayList<>();
        if(!command.getCommandParameter().isEmpty()){
            commandParaSet=command.getCommandParameter();
        }
        for(int x=0; x<parameters.length; x++){
            String para=parameters[x];
            CommandParameter newPara=new CommandParameter();
            Object comp=components.get(para);
            if(comp!=null){
                newPara.setCommand(command);
                newPara.setBackgroundKey(para);
                newPara.setComponent(components.get(para));
                if(para=="pin"){
                    newPara.setPin(true);
                }
                if(para=="output"){
                    newPara.setClassName("BackgroundTask");
                }else newPara.setClassName("BackgroundVariables");
                if(!commandParaSet.contains(newPara)){
                    commandParaSet.add(newPara);
                }
            }
        }
        return commandParaSet;
    }

    public CommandParameter createParameter(String type,int order){
        CommandParameter newPara=new CommandParameter();
        newPara.setType(type);
        newPara.setParameterOrder(order+1);
        if(type=="pin") newPara.setPin(true);
        return newPara;
    }

    public CommandFunction[] getCommandArr() {
        return commandArr;
    }
   
    
}
