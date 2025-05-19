
package com.scheduler.Base.MapCast.StoreValue;

public class StringStore extends StoreBase {
    
    private String value;

    public StringStore() {
    }

    public StringStore(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringStore value(String value) {
        setValue(value);
        return this;
    }

    
}
