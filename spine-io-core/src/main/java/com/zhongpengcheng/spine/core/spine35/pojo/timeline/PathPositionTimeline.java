package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-16 14:24:47
 **/
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

    public int getPathIndex() {
        return this.pathIndex;
    }

    public String getPathName() {
        return this.pathName;
    }

    public String getTimelineType() {
        return this.timelineType;
    }

    public List<Frame> getFrameList() {
        return this.frameList;
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
        @Expose
        private float time;
        @Expose
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
