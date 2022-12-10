package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongpengcheng
 * @since 2022-02-15 16:20:38
 **/
@Getter
@Setter
public class RotateTimeline extends CurveTimeline {
    @Expose
    private int boneIndex;
    @Expose
    private String boneName;
    @Expose
    private List<Frame> frameList;
    @Expose
    private String timelineType;

    public RotateTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float degrees) {
        this.frameList.add(Frame.builder()
                .time(time)
                .degrees(degrees)
                .build());
    }

    @Data
    @Builder
    public static class Frame {
        @Expose
        private float time;
        @Expose
        private float degrees;
    }
}
