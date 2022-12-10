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
 * @since 2022-02-15 16:31:16
 **/
@Getter
@Setter
public class TranslateTimeline extends CurveTimeline {
    @Expose
    private int boneIndex;
    @Expose
    private String boneName;
    @Expose
    private List<Frame> frameList;
    @Expose
    private String timelineType;

    public TranslateTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float x, float y) {
        this.frameList.add(Frame.builder()
                .time(time)
                .x(x)
                .y(y)
                .build());
    }

    @Data
    @Builder
    public static class Frame {
        @Expose
        private float time;
        @Expose
        private float x;
        @Expose
        private float y;
    }
}
