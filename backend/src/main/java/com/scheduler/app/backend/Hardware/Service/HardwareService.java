package com.scheduler.app.backend.Hardware.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.app.backend.Hardware.Models.Hardware;
import com.scheduler.app.backend.Hardware.Models.HardwarePins;
import com.scheduler.app.backend.Hardware.Repo.HardwarePinsRepo;
import com.scheduler.app.backend.Hardware.Repo.HardwareRepo;

@Configuration
@Service
public class HardwareService extends Base {
    public final HardwareRepo hardware;
    public final HardwarePinsRepo pins;
    
    private final Hardware boards[]={
        new Hardware("ESP8266", 80192,null, null)
    };
    private HashMap<String,Integer> pinMap=new HashMap<>();

    public HardwareService(HardwareRepo hardware,HardwarePinsRepo pins){
        this.hardware=hardware;
        this.pins=pins;
        pinMap.put("D0", 16);
        pinMap.put("D1", 5);
        pinMap.put("D2", 4);
        pinMap.put("D3", 0);
        pinMap.put("D4", 2);
        pinMap.put("D5", 14);
        pinMap.put("D6", 12);
        pinMap.put("D7", 13);
        pinMap.put("D8", 15);
    }

    public void initData(){
        for(int i=0; i<boards.length; i++){
            Hardware board=boards[i];
            Hardware existBoard=hardware.findHardwareByBoardName(board.getBoardName());
            if(existBoard!=null){

            }else
            {
                board.setPins(createPinList(pinMap, board));
                hardware.save(board);

            }
        }
    }
    public List<HardwarePins> createPinList( HashMap<String,Integer> list,Hardware hard){
        List<HardwarePins> pinList=new ArrayList<>();
        for(String i:list.keySet()){
            HardwarePins newPin=new HardwarePins(hard,i,list.get(i));
            pinList.add(newPin);
        }
        return pinList;
    }
    public List<Hardware> getBoards(){
        return hardware.findAll();
    }
}
