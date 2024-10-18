package com.scheduler.Base.MapCast.StoreValue;

import java.util.List;
public class ArrayListStoreString extends StoreBase{

    List<String>value;

    public ArrayListStoreString() {
    }

    public ArrayListStoreString(List<String> value) {
        this.value = value;
    }

    public List<String> getValue() {
        return this.value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public ArrayListStoreString value(List<String> value) {
        setValue(value);
        return this;
    }


}
