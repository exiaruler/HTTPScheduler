package com.scheduler.app.backend.Command.Application;

import com.scheduler.app.backend.Command.CommandFunction;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
@Entity
public class MobileSuit extends CommandFunction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private boolean limitedEnergy=false;
    @Column
    private int powerSupplyTotal=1000;
    @Column
    private int powerSupply;
    @Column
    private boolean propellent=true;
    @Column
    private int propellentSupplyTotal=1000;
    @Column
    private int propellentSupply;
    @Column
    private boolean thrusterActive;
    @Column
    private boolean docked=false;

    public boolean activeMSCathode(String ip,String [] arr){
        boolean success=false;
        arr[1]="255";
        setLedCathode(ip, arr);
        arr[1]="205";
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
            setLedCathode(ip, arr);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return success;
    }
    public boolean activeMSAnode(String ip,String []arr){
        boolean success=false;
        String [] setArr={arr[0],"255"};
        setLedAnode(ip,setArr);
        setArr[1]="205";
        try {
            miliSecondDelay(1000);
            success=setLedAnode(ip,setArr);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return success;
    }
    public boolean thrusterControlAnode(String ip,String []arr){
        boolean success=false;
        if(this.thrusterActive){
            success=setLedAnode(ip,arr);
        }
        if(!this.thrusterActive){
            try {
                for(int i=1; i<6; i++){
                    String [] setArr={arr[0],"100"};
                    setLedAnode(ip,setArr);
                    miliSecondDelay(1300);
                    String []offArr={arr[0],"10"};
                    success=setLedAnode(ip,offArr);
                }
                success=setLedAnode(ip,arr);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return success;
    }
}
