package com.zhongpengcheng.spine.io.pojo;

import com.google.gson.annotations.Expose;

/**
 * @author skyfire33
 * @since 2021-03-03 14:28:00
 */
public class Event {
    @Expose
    String name;
    @Expose
    Integer aInt = 0;
    @Expose
    Float aFloat = 0F;
    @Expose
    String aString = "";

    public Event(String name, Integer aInt, Float aFloat, String aString) {
        this.name = name;
        this.aInt = aInt;
        this.aFloat = aFloat;
        this.aString = aString;
    }

    public Event() {
    }

    public String getName() {
        return this.name;
    }

    public Integer getAInt() {
        return this.aInt;
    }

    public Float getAFloat() {
        return this.aFloat;
    }

    public String getAString() {
        return this.aString;
    }

    public Event setName(String name) {
        this.name = name;
        return this;
    }

    public Event setAInt(Integer aInt) {
        this.aInt = aInt;
        return this;
    }

    public Event setAFloat(Float aFloat) {
        this.aFloat = aFloat;
        return this;
    }

    public Event setAString(String aString) {
        this.aString = aString;
        return this;
    }
}
