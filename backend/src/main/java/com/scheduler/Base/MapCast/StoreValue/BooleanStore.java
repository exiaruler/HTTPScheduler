package com.scheduler.Base.MapCast.StoreValue;
import java.util.Objects;

public class BooleanStore extends StoreBase {
    boolean value;

    public BooleanStore() {
    }

    public BooleanStore(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return this.value;
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public BooleanStore value(boolean value) {
        setValue(value);
        return this;
    }
    
}
