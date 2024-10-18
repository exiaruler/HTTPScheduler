package com.scheduler.app.backend.Command.Service;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.Command.CommandFunction;
import com.scheduler.app.backend.Command.Models.Command;
import com.scheduler.app.backend.Command.Repo.CommandRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import java.util.*;

@Configuration
@Service
public class CommandService extends Base {
    public final CommandRepo command;
    public CommandFunction function=new CommandFunction();
    private Command commandArr[]=function.commandArr;
    //public TaskService taskService;
    public CommandService(CommandRepo command) {
        this.command = command;
        
    }
    public Command getCommand(long id){
        return command.getReferenceById(id);
    }
    @Bean(initMethod="init")
    public void initData(){
        for(int i=0; i<commandArr.length; i++){
            Command commandItem=commandArr[i];
            Command exist=command.findRouteByClass(commandItem.getCommand(), commandItem.getClassName());
            if(exist==null){
                command.save(commandItem);
            }else{
                exist.setClassName(commandItem.getClassName());
                exist.setTotalParam(commandItem.getTotalParam());
                exist.setCommand(commandItem.getCommand());
                exist.setHasMotor(commandItem.getHasMotor());
                exist.setSynchronous(commandItem.getSynchronous());
                command.save(exist);
            }
        }
    }
   
    
}
