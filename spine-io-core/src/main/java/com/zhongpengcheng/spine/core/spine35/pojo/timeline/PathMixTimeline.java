package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-16 14:38:46
 **/
@Getter
public class PathMixTimeline extends CurveTimeline {
    
    private int pathIndex;
    
    private String pathName;
    
    private String timelineType = "mix";
    
    private List<Frame> frameList;

    public PathMixTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float rotateMix, float translateMix) {
        this.frameList.add(new Frame(time, rotateMix, translateMix));
    }

    public void setPathIndex(int pathIndex) {
        this.pathIndex = pathIndex;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public void setTimelineType(String timelineType) {
        this.timelineType = timelineType;
    }

    public void setFrameList(List<Frame> frameList) {
        this.frameList = frameList;
    }

    @Getter
    public static class Frame {
        
        private float time;
        
        private float rotateMix;
        
        private float translateMix;

        public Frame(float time, float rotateMix, float translateMix) {
            this.time = time;
            this.rotateMix = rotateMix;
            this.translateMix = translateMix;
        }

        public Frame() {
        }

        public void setTime(float time) {
            this.time = time;
        }

        public void setRotateMix(float rotateMix) {
            this.rotateMix = rotateMix;
        }

        public void setTranslateMix(float translateMix) {
            this.translateMix = translateMix;
        }
    }
}
