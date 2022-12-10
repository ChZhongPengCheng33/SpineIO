package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongpengcheng
 * @since 2022-02-16 15:53:35
 **/
@Getter
@Setter
public class EventTimeline implements ITimeline {
    @Expose
    private String eventName;
    @Expose
    private List<Frame> frameList;

    public EventTimeline (int frameCount) {
        frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, String name, int intValue, float floatValue, String stringValue) {
        frameList.add(new Frame(time, name, intValue, floatValue, stringValue));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Frame {
        @Expose
        private float time;
        @Expose
        private String name;
        @Expose
        private int intValue;
        @Expose
        private float floatValue;
        @Expose
        private String stringValue;
    }

}
