package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongpengcheng
 * @since 2022-02-15 17:13:48
 **/
@Getter
@Setter
@Accessors(chain = true)
public class IkTimeline extends CurveTimeline {

    @Expose
    private int ikIndex;
    @Expose
    private String ikName;
    @Expose
    private List<Frame> frameList;

    public IkTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float mix, int bendDirection) {
        this.frameList.add(new Frame(time, mix, bendDirection));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Frame {
        @Expose
        private float time;
        @Expose
        private float mix;
        @Expose
        private int bendDirection;
    }
}
