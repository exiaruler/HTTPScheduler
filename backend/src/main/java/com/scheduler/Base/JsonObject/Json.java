package com.scheduler.Base.JsonObject;

import java.util.ArrayList;

public interface Json {
    public String findKeyValue(String key);
    public boolean checkKey(String key);
    public JsonObject jsonToObject(String json);
    public ArrayList<String> findKeyToArray(String key,String split);

}