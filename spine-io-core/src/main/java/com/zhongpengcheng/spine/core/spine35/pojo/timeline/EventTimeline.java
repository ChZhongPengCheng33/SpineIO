package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-16 15:53:35
 **/
@Getter
public class EventTimeline implements ITimeline {
    
    private String eventName;
    
    private List<Frame> frameList;

    public EventTimeline(int frameCount) {
        frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, String name, int intValue, float floatValue, String stringValue) {
        frameList.add(new Frame(time, name, intValue, floatValue, stringValue));
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setFrameList(List<Frame> frameList) {
        this.frameList = frameList;
    }

    public static class Frame {
        
        private float time;
        
        private String name;
        
        private int intValue;
        
        private float floatValue;
        
        private String stringValue;

        public Frame(float time, String name, int intValue, float floatValue, String stringValue) {
            this.time = time;
            this.name = name;
            this.intValue = intValue;
            this.floatValue = floatValue;
            this.stringValue = stringValue;
        }

        public Frame() {
        }

        public float getTime() {
            return this.time;
        }

        public String getName() {
            return this.name;
        }

        public int getIntValue() {
            return this.intValue;
        }

        public float getFloatValue() {
            return this.floatValue;
        }

        public String getStringValue() {
            return this.stringValue;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIntValue(int intValue) {
            this.intValue = intValue;
        }

        public void setFloatValue(float floatValue) {
            this.floatValue = floatValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }
    }

}
