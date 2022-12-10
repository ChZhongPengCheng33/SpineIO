package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongpengcheng
 * @since 2022-02-16 14:24:47
 **/
@Getter
@Setter
public class PathPositionTimeline extends CurveTimeline {
    @Expose
    private int pathIndex;
    @Expose
    private String pathName;
    @Expose
    private String timelineType = "position";
    @Expose
    private List<Frame> frameList;

    public PathPositionTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float position) {
        this.frameList.add(new Frame(time, position));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Frame {
        @Expose private float time;
        @Expose private float position;
    }
}
