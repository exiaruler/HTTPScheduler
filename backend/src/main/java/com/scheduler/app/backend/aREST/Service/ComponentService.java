package com.scheduler.app.backend.aREST.Service;
import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.aREST.Models.Component;
import com.scheduler.app.backend.aREST.Models.Device;
import com.scheduler.app.backend.aREST.Repo.ComponentRepo;
import com.scheduler.app.backend.aREST.Repo.DeviceRepo;
@Service
public class ComponentService extends Base {
    private final ComponentRepo componentRepo;
    private final DeviceRepo deviceRepo;
    public ComponentService(ComponentRepo componentRepo, DeviceRepo deviceRepo){
        this.componentRepo = componentRepo;
        this.deviceRepo = deviceRepo;
        
    }
    // Digital pins
    private String[] pinDigArr={"D0","D1","D2","D3","D4","D5","D6","D7","D8","D9","D10"};
    
    // use for api
    public String[] getDigitalPins(){
        return pinDigArr;
    }
    public Component newComponent(){
        Component newComp=new Component();
        return newComp;
    }

    public Component addComponent(long deviceId,Component comp){
        Device device=deviceRepo.getReferenceById(deviceId);
        comp.setDevice(device);
        
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
    public String [] digitalPinAll(){
        return pinDigArr;
    }
    public Component getComponent(long id){
        return componentRepo.getReferenceById(id);
    }
    /* 
    public List<Component> getAllComponentByDevice(){

    }
    */
}
