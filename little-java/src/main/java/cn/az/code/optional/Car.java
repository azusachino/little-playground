package cn.az.code.optional;

import java.util.Optional;

/**
 * @author Liz
 * @date 1/9/2020
 */
public class Car {

    private String name;
    private String val;

    private Insurance insurance;

    public Car() {
    }

    public Car(String name, String val, Insurance insurance) {
        this.name = name;
        this.val = val;
        this.insurance = insurance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
