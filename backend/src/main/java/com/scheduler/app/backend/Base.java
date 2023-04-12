package com.scheduler.app.backend;
import com.scheduler.app.HTTPHandle.HttpUtil;

// template class 
public class Base{

    protected HttpUtil httpUtil=new HttpUtil();


    // check if the device is an arduino board using aREST
    public boolean arduinoboardCheck(String jsonString){
        String [] arr=jsonString.split("},");
        if(arr.length==2){
            return true;
        }
        return false;
    }
    // get part from json string. variable or device
    public String getrawPart(String jsonString,int part){
        String [] arr=jsonString.split("},");
        String output="";
        output=arr[part];
        if(part==1){
            int curlyBracesFind=output.indexOf("}");
            output=output.substring(0, curlyBracesFind);
        }
        output=output.replaceAll("\"","");
        return output;
    }
    public String getrawVariable(String jsonString){
        int startBrace=jsonString.indexOf("{");
        int endBrace=jsonString.indexOf("}");
        jsonString=jsonString.substring(startBrace+1, endBrace);
        jsonString=jsonString.replaceAll("\"","");
        return jsonString;
    }
    // get field from json for board
    public String getDataByFieldBoard(String field,String input){
        field=" "+field;
        String [] arr=input.split(":");
        String output="";
        if(arr[0].equals(field)){
            output=arr[1];
        }
        return output;
    }

    // get field from json
    public String getDataByField(String field,String input){
        String [] arr=input.split(":");
        String output="";
        if(arr[0].equals(field)){
            output=arr[1];
        }
        return output;
    }
    public String getVariableData(String value){
        if(value=="") return "";
        int index=value.indexOf(": {");
        value=value.substring(index);
        index=value.indexOf("{");
        value=value.substring(index+1);
        return value;
    }
    
    public Object test(Object object){
        Object test=new Object();
        if(object!=null){
            test=object;
        }
        return test;
    }
    
}