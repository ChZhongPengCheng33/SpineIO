package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongpengcheng
 * @since 2022-02-16 14:38:46
 **/
@Getter
@Setter
public class PathMixTimeline extends CurveTimeline {
    @Expose
    private int pathIndex;
    @Expose
    private String pathName;
    @Expose
    private String timelineType = "mix";
    @Expose
    private List<Frame> frameList;

    public PathMixTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float rotateMix, float translateMix) {
        this.frameList.add(new Frame(time, rotateMix, translateMix));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Frame {
        @Expose
        private float time;
        @Expose
        private float rotateMix;
        @Expose
        private float translateMix;
    }
}
