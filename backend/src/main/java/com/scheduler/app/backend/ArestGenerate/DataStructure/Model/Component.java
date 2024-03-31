package com.scheduler.app.backend.ArestGenerate.DataStructure.Model;

import java.util.ArrayList;
import java.util.Objects;

public class Component {
    private String part;
    private int pin=0;
    private boolean rgb=false;
    private boolean main=false;
    private ArrayList<Integer> rgbPins=new ArrayList<Integer>(3);
    private boolean motor=false;
    private boolean pinModeOut=false;
    private boolean pinmodeIn=false;
    private boolean active=false;

    public Component() {
    }

    public Component(String part, int pin, boolean rgb, boolean main, ArrayList<Integer> rgbPins, boolean motor, boolean pinModeOut, boolean pinmodeIn, boolean active) {
        this.part = part;
        this.pin = pin;
        this.rgb = rgb;
        this.main = main;
        this.rgbPins = rgbPins;
        this.motor = motor;
        this.pinModeOut = pinModeOut;
        this.pinmodeIn = pinmodeIn;
        this.active = active;
    }

    public String getPart() {
        return this.part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public int getPin() {
        return this.pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean isRgb() {
        return this.rgb;
    }

    public boolean getRgb() {
        return this.rgb;
    }

    public void setRgb(boolean rgb) {
        this.rgb = rgb;
    }

    public boolean isMain() {
        return this.main;
    }

    public boolean getMain() {
        return this.main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public ArrayList<Integer> getRgbPins() {
        return this.rgbPins;
    }

    public void setRgbPins(ArrayList<Integer> rgbPins) {
        this.rgbPins = rgbPins;
    }

    public boolean isMotor() {
        return this.motor;
    }

    public boolean getMotor() {
        return this.motor;
    }

    public void setMotor(boolean motor) {
        this.motor = motor;
    }

    public boolean isPinModeOut() {
        return this.pinModeOut;
    }

    public boolean getPinModeOut() {
        return this.pinModeOut;
    }

    public void setPinModeOut(boolean pinModeOut) {
        this.pinModeOut = pinModeOut;
    }

    public boolean isPinmodeIn() {
        return this.pinmodeIn;
    }

    public boolean getPinmodeIn() {
        return this.pinmodeIn;
    }

    public void setPinmodeIn(boolean pinmodeIn) {
        this.pinmodeIn = pinmodeIn;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Component part(String part) {
        setPart(part);
        return this;
    }

    public Component pin(int pin) {
        setPin(pin);
        return this;
    }

    public Component rgb(boolean rgb) {
        setRgb(rgb);
        return this;
    }

    public Component main(boolean main) {
        setMain(main);
        return this;
    }

    public Component rgbPins(ArrayList<Integer> rgbPins) {
        setRgbPins(rgbPins);
        return this;
    }

    public Component motor(boolean motor) {
        setMotor(motor);
        return this;
    }

    public Component pinModeOut(boolean pinModeOut) {
        setPinModeOut(pinModeOut);
        return this;
    }

    public Component pinmodeIn(boolean pinmodeIn) {
        setPinmodeIn(pinmodeIn);
        return this;
    }

    public Component active(boolean active) {
        setActive(active);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Component)) {
            return false;
        }
        Component component = (Component) o;
        return Objects.equals(part, component.part) && pin == component.pin && rgb == component.rgb && main == component.main && Objects.equals(rgbPins, component.rgbPins) && motor == component.motor && pinModeOut == component.pinModeOut && pinmodeIn == component.pinmodeIn && active == component.active;
    }

    @Override
    public int hashCode() {
        return Objects.hash(part, pin, rgb, main, rgbPins, motor, pinModeOut, pinmodeIn, active);
    }

    @Override
    public String toString() {
        return "{" +
            " part='" + getPart() + "'" +
            ", pin='" + getPin() + "'" +
            ", rgb='" + isRgb() + "'" +
            ", main='" + isMain() + "'" +
            ", rgbPins='" + getRgbPins() + "'" +
            ", motor='" + isMotor() + "'" +
            ", pinModeOut='" + isPinModeOut() + "'" +
            ", pinmodeIn='" + isPinmodeIn() + "'" +
            ", active='" + isActive() + "'" +
            "}";
    }
    
}
