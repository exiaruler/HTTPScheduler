package com.scheduler.app.backend.aREST.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.aREST.Models.Component;
import com.scheduler.app.backend.aREST.Repo.ComponentRepo;
@Service
public class ComponentService extends Base {
    private final ComponentRepo componentRepo;
    // Digital pins
    private String[] pinDigArr={"D1","D2","D3","D4","D5","D6","D7","D8","D9","D10"};
    public ComponentService(ComponentRepo componentRepo){
        this.componentRepo = componentRepo;

    }

    // use for api
    public String[] getDigitalPins(){
        return pinDigArr;
    }
    public Component addComponent(Component comp){
        return componentRepo.save(comp);
    }
    public Component updateComponent(long id,Component comp){
        Component exist=componentRepo.getReferenceById(id);
        if(exist!=null){
            exist=comp;
            componentRepo.save(exist);
        }
        return exist;
    }
    /* 
    public List<Component> getAllComponentByDevice(){

    }
    */
}
