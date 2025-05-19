package com.scheduler.app.backend.InterfaceModels.Input;
import java.util.Objects;
// param input
public class ModeParameterInput {
    private String backgroundKey;
    private String value;
    private int[] valueArr;
    private int valueNumber;


    public ModeParameterInput() {
    }

    public ModeParameterInput(String backgroundKey, String value, int[] valueArr, int valueNumber) {
        this.backgroundKey = backgroundKey;
        this.value = value;
        this.valueArr = valueArr;
        this.valueNumber = valueNumber;
    }

    public String getBackgroundKey() {
        return this.backgroundKey;
    }

    public void setBackgroundKey(String backgroundKey) {
        this.backgroundKey = backgroundKey;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int[] getValueArr() {
        return this.valueArr;
    }

    public void setValueArr(int[] valueArr) {
        this.valueArr = valueArr;
    }

    public int getValueNumber() {
        return this.valueNumber;
    }

    public void setValueNumber(int valueNumber) {
        this.valueNumber = valueNumber;
    }

    public ModeParameterInput backgroundKey(String backgroundKey) {
        setBackgroundKey(backgroundKey);
        return this;
    }

    public ModeParameterInput value(String value) {
        setValue(value);
        return this;
    }

    public ModeParameterInput valueArr(int[] valueArr) {
        setValueArr(valueArr);
        return this;
    }

    public ModeParameterInput valueNumber(int valueNumber) {
        setValueNumber(valueNumber);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ModeParameterInput)) {
            return false;
        }
        ModeParameterInput modeParameterInput = (ModeParameterInput) o;
        return Objects.equals(backgroundKey, modeParameterInput.backgroundKey) && Objects.equals(value, modeParameterInput.value) && Objects.equals(valueArr, modeParameterInput.valueArr) && valueNumber == modeParameterInput.valueNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(backgroundKey, value, valueArr, valueNumber);
    }

    @Override
    public String toString() {
        return "{" +
            " backgroundKey='" + getBackgroundKey() + "'" +
            ", value='" + getValue() + "'" +
            ", valueArr='" + getValueArr() + "'" +
            ", valueNumber='" + getValueNumber() + "'" +
            "}";
    }
    

}
