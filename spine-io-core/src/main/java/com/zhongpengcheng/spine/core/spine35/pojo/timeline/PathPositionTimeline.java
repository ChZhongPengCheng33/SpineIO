package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-16 14:24:47
 **/
@Getter
public class PathPositionTimeline extends CurveTimeline {
    
    private int pathIndex;
    
    private String pathName;
    
    private String timelineType = "position";
    
    private List<Frame> frameList;

    public PathPositionTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float position) {
        this.frameList.add(new Frame(time, position));
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

    public static class Frame {
        
        private float time;
        
        private float position;

        public Frame(float time, float position) {
            this.time = time;
            this.position = position;
        }

        public Frame() {
        }

        public float getTime() {
            return this.time;
        }

        public float getPosition() {
            return this.position;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public void setPosition(float position) {
            this.position = position;
        }
    }
}
