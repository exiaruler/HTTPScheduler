package com.scheduler.Base.MapCast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scheduler.Base.MapCast.StoreValue.ArrayListStoreString;
import com.scheduler.Base.MapCast.StoreValue.BooleanStore;
import com.scheduler.Base.MapCast.StoreValue.IntegerStore;
import com.scheduler.Base.MapCast.StoreValue.StringStore;
import java.util.Set;
// map json from request
public class MapCast {
    // store string values
    private List<StringStore> stringStore=new ArrayList<StringStore>();
    // stores integer values
    private List<IntegerStore> integerStore=new ArrayList<IntegerStore>();
    // store boolean values
    private List<BooleanStore> booleanStore=new ArrayList<BooleanStore>();
    // store arraylist strings
    private List<ArrayListStoreString> arrayListStoreString=new ArrayList<ArrayListStoreString>();
    private String key;
    private int index;
    private String dataType;
    // store list indexes and location of where that data is located
    private HashMap<String,MapCast> map=new HashMap<String,MapCast>();

    public MapCast() {
    }

    public MapCast(List<StringStore> stringStore, String key, int index, String dataType,HashMap<String,MapCast> map) {
        this.stringStore = stringStore;
        this.key = key;
        this.index = index;
        this.dataType = dataType;
        this.map = map;
    }

    public String getKeyString(String key){
      String value="";
      MapCast obj=map.get(key);
      if(obj!=null){
        MapCast map=obj;
        value=stringStore.get(map.index).getValue();
      }
      return value;
    }
    public int getKeyInteger(String key){
        int value=-1;
        MapCast obj=map.get(key);
        if(obj!=null){
          MapCast map=obj;
          value=integerStore.get(map.index).getValue();
        }
        return value;
    }
    public boolean getKeyBoolean(String key){
        boolean value=false;
        MapCast obj=map.get(key);
        if(obj!=null){
          MapCast map=obj;
          value=booleanStore.get(map.index).getValue();
        }
        return value;
    }
    public List<String> getKeyArrayListString(String key){
        List<String>value=new ArrayList<String>();
        MapCast obj=map.get(key);
        if(obj!=null){
            MapCast map=obj;
            value=arrayListStoreString.get(map.index).getValue();
        }
        return value;
    }

    public MapCast mapJson(Map<String, Object> payload){
        MapCast cast=new MapCast();
        Set<String> keys=payload.keySet();
        for(String i:keys){
            Object object=payload.get(i);
            cast=addToStore(object, i,cast);
            
        }
        return cast;
    }
    
    private MapCast addToStore(Object object,String key,MapCast cast){
        String dataTypeClass=object.getClass().getTypeName();
        int index=0;
        boolean add=false;
        switch (dataTypeClass) {
            case "java.lang.String":
                StringStore store=new StringStore();
                store.setKey(key);
                String value=(String) object;
                store.setValue(value);
                cast.stringStore.add(store);
                index=calculateIndex(cast.stringStore.size());
                add=true;
                break;
            case "java.lang.Integer":
                IntegerStore storeInt=new IntegerStore();
                storeInt.setKey(key);
                int valueInt=(int) object;
                storeInt.setValue(valueInt);
                cast.integerStore.add(storeInt);
                index=calculateIndex(cast.integerStore.size());
                add=true;
                break;
            case "java.lang.Boolean":
                BooleanStore storeBool=new BooleanStore();
                storeBool.setKey(key);
                boolean valueBool=(boolean) object;
                storeBool.setValue(valueBool);
                cast.booleanStore.add(storeBool);
                index=calculateIndex(cast.booleanStore.size());
                add=true;
                break;
            case "java.util.ArrayList":
                ArrayListStoreString storeArrString=new ArrayListStoreString();
                storeArrString.setKey(key);
                @SuppressWarnings("unchecked") List<String> valueListString=(List<String>) object;
                storeArrString.setValue(valueListString);
                cast.arrayListStoreString.add(storeArrString);
                index=calculateIndex(cast.arrayListStoreString.size());
                add=true;
                break;
            default:
                break;
        }
        if(add){
            MapCast newkey=new MapCast();
            newkey.setIndex(index);
            newkey.setKey(key);
            newkey.setDataType(dataTypeClass);
            cast.map.put(key, newkey);
        }
        return cast;
    }
    private int calculateIndex(int length){
        return length-1;
    }

    public String getKey() {
        return this.key;
    }

    private void setKey(String key) {
        this.key = key;
    }

    private void setIndex(int index) {
        this.index = index;
    }

    public String getDataType() {
        return this.dataType;
    }

    private void setDataType(String dataType) {
        this.dataType = dataType;
    }

 

  

    

    

}
