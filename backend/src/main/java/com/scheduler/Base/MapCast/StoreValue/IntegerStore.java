package com.scheduler.Base.MapCast.StoreValue;
import java.util.Objects;

public class IntegerStore extends StoreBase {
    int value;
    
    public IntegerStore() {
    }

    public IntegerStore(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public IntegerStore value(int value) {
        setValue(value);
        return this;
    }
}
