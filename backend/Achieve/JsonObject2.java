package com.scheduler.Base.JsonObject;

import java.util.*;

public class JsonObject2 extends JsonObject {
    // store json key and values
    ArrayList<JsonObject> variables=new ArrayList<JsonObject>();
    // store key index for search in variables
    ArrayList<String> keyIndex=new ArrayList<String>();

    public JsonObject2 jsonToObject(String json){
        JsonObject2 obj=new JsonObject2();
        json=json.replaceAll("\"", "");
        int startBrace=json.indexOf("{");
        int endBrace=-1;
        for(int x=json.length()-1; x>0; --x){
            char character=json.charAt(x);
            if(character=='}'){
                endBrace=x;
                break;
            }
        }
        json=json.substring(startBrace+1,endBrace);
        // add json within first 
        int stringIndex=0;
        int startIndex=-1,endIndex=-1;
        while(json.contains("{")&&json.contains("}")){
            char character=json.charAt(stringIndex);
            if(character=='{'&&startIndex==-1){
                startIndex=stringIndex;
            }
            if(character=='}'&&endIndex==-1&&startIndex>-1){
                endIndex=stringIndex;
            }
            if(startIndex>-1&&endIndex>-1){
                // get key
                String outKey=getKeyForObject(json, startIndex);
                keyIndex.add(outKey);
                String variable=json.substring(startIndex+1,endIndex);
                String vArr[]=variable.split(",");
                for(int i=0; i<vArr.length; i++){
                    JsonObject jsonOb=new JsonObject();
                    String [] arr=vArr[i].split(":");
                    String key=arr[0];
                    String value=arr[1];
                    //obj=addJson(arr[0],arr[1],jsonOb);
                }
                json=json.substring(endIndex+2,json.length());
                if(json.contains("{")&&json.contains("}")){
                    startIndex=-1;
                    endIndex=-1;
                }
            }
            stringIndex++;
        }
        return obj;
    }
    private JsonObject addJsonObject(String key,String value){
        JsonObject jsonOb=new JsonObject();
        Variable obj=new Variable();
        //ArrayList<Variable> variableList=new ArrayList<Variable>();
        jsonOb.keyIndex.add(key);
        //jsonOb.variables=variableList;
        //obj.
        return jsonOb;
    }
    private String getKeyForObject(String json,int startIndex){
        String key="";
        // first find :
        char point=' ';
        while(point!=':'){
            point=json.charAt(startIndex);
            startIndex--;
        }
        int begin=startIndex;
        // find index of ,
        point=' ';
        while(point!=','){
            point=json.charAt(startIndex);
            startIndex--;
        }
        int end=startIndex;
        key=json.substring(begin,end);
        // return key
        return key;
    }
    
}
