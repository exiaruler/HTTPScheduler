package com.scheduler.Base;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import javax.transaction.Transactional;

import com.scheduler.Base.JsonObject.JsonObject;
import com.scheduler.app.backend.HTTPHandle.HttpUtil;

// template class 
public class Base{

    protected HttpUtil httpUtil=new HttpUtil();
    public JsonObject jsonobj=new JsonObject();

    // arest v2 get value from query route
    public String requestQuery(String ip,String param){
        String responseBody="";
        long standard=200;
        String routeSet=httpUtil.createRoute(ip,"query",param);
        try {
            String rawReturn=httpUtil.httpRequest(routeSet,standard);
            JsonObject returnJson=jsonobj.jsonToObject(rawReturn);
            if(returnJson.findKeyValue("return_value").trim().equals("1")){
                String queryDataRoute=httpUtil.createRoute(ip,"QueryData","");
                String queryDataRaw=httpUtil.httpRequest(queryDataRoute, standard);
                returnJson=jsonobj.jsonToObject(queryDataRaw);
                responseBody=returnJson.findKeyValue("QueryData").trim();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

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
        if(arr[0].toLowerCase().equals(field.toLowerCase())){
            output=arr[1];
        }
        return output;
    }
    public String getDataByFieldRevamp(String field,String input){
        String data="";
        String [] arr=input.split(",");
        for(int i=0; i<arr.length; i++){
            String jsonRow=arr[i];
            String [] jsonSplit=jsonRow.split(":");
            if(field.toLowerCase().equals(jsonSplit[0].toLowerCase())){
                data=jsonSplit[1].trim();
                break;
            }
        }
        return data;
    }
    // get object in json aREST
    public String getVariableData(String value){
        if(value=="") return "";
        int index=value.indexOf(": {");
        value=value.substring(index);
        index=value.indexOf("{");
        value=value.substring(index+1);
        return value;
    }
    // generate random string
    public String generateRandString(int length){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilt = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            stringBuilt.append(characters.charAt(index));
        }
        return stringBuilt.toString();
    }
}