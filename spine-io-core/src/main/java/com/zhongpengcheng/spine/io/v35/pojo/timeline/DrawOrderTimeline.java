package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongpengcheng
 * @since 2022-02-16 15:31:31
 **/
@Getter
@Setter
public class DrawOrderTimeline implements ITimeline {
    @Expose
    private List<Frame> frameList;

    public DrawOrderTimeline (int frameCount) {
        frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, List<Offset> offsets) {
        this.frameList.add(new Frame(time, offsets));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Frame {
        @Expose
        private float time;
        @Expose
        private List<Offset> offsets;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Offset {
        @Expose
        private int slotIndex;
        @Expose
        private int offset;
        @Expose
        private String slotName;
    }
}
