package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongpengcheng
 * @since 2022-02-16 14:47:15
 **/
@Getter
@Setter
public class DeformTimeline extends CurveTimeline {
    @Expose
    private int slotIndex;
    @Expose
    private int skinIndex;
    @Expose
    private String slotName;
    @Expose
    private String skinName;
    @Expose
    private String attachmentName;
    @Expose
    private List<Frame> frameList;

    public DeformTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float[] vertices) {
        this.frameList.add(new Frame(time, vertices));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Frame {
        @Expose
        private float time;
        @Expose
        private float[] vertices;
    }
}
