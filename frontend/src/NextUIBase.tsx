'use client'
import UiBase from "./base/UiBase";
import { NextBase } from "./NextBase";
// base for UI
export class NextUIBase extends UiBase{
    public util=new NextBase();

    public generateKeyClient(){
        var key=this.util.generateEncryptKey();
        sessionStorage.setItem(this.util.originUrl+"-en",key);
    }
    public checkKey(){
        var key=sessionStorage.getItem(this.util.originUrl+"-en");
        return key;
    }   
}