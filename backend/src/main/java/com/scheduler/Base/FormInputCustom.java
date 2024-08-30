package com.scheduler.Base;

import java.util.ArrayList;
import java.util.List;

public class FormInputCustom {
    private final List<String> componentSelection=new ArrayList<String>();
    private String key;
    private String component;


    public FormInputCustom() {
        this.componentSelection.add("text");
        this.componentSelection.add("dropdown");
    }
    public String getComponentPart(String comp){
        String part="";
        int index=this.componentSelection.indexOf(comp);
        if(index>-1) part=this.componentSelection.get(index);
        return part;
    }
    public FormInputCustom(String key, String component) {
        this.key = key;
        this.component = component;
    }


    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getComponent() {
        return this.component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
    
    

}
