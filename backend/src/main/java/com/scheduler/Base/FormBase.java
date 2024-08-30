package com.scheduler.Base;

import java.util.ArrayList;
import java.util.List;

// form data structure to use to add new forms to the portfolio API
public class FormBase {
    private Object model;
    private List<String> keysNotUse=new ArrayList<String>();
    private List<FormInputCustom> customComponents=new ArrayList<FormInputCustom>();
    public FormBase() {
        
        keysNotUse.add("createdDate");
        keysNotUse.add("updatedDate");
        keysNotUse.add("id");
    }

    public void addCustomInput(String key,String comp){
        FormInputCustom rec=new FormInputCustom();
        rec.setKey(key);
        String part=rec.getComponentPart(comp);
        rec.setComponent(part);
        if(part!="")customComponents.add(rec);

    }

    public void addNotUseKey(String key){
        this.keysNotUse.add(key);
    }


    public Object getModel() {
        return this.model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public List<String> getKeysNotUse() {
        return this.keysNotUse;
    }

    public void setKeysNotUse(List<String> keysNotUse) {
        this.keysNotUse = keysNotUse;
    }

    public List<FormInputCustom> getCustomComponents() {
        return this.customComponents;
    }

    public void setCustomComponents(List<FormInputCustom> customComponents) {
        this.customComponents = customComponents;
    }
    
    
}
