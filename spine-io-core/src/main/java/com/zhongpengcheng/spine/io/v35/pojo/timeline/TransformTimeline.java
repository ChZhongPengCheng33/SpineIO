package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongpengcheng
 * @since 2022-02-16 10:59:58
 **/
@Getter
@Setter
public class TransformTimeline extends CurveTimeline {
    @Expose private int transformIndex;
    @Expose private String transformName;
    @Expose
    private List<Frame> frameList;

    public TransformTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float rotateMix, float translateMix, float scaleMix, float shearMix) {
        this.frameList.add(new Frame(time, rotateMix, translateMix, scaleMix, shearMix));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Frame {
        @Expose private float time;
        @Expose private float rotateMix;
        @Expose private float translateMix;
        @Expose private float scaleMix;
        @Expose private float shearMix;
    }
}
