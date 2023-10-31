package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-16 14:38:46
 **/
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
        private float rotateMix;
        @Expose
        private float translateMix;

        public Frame(float time, float rotateMix, float translateMix) {
            this.time = time;
            this.rotateMix = rotateMix;
            this.translateMix = translateMix;
        }

        public Frame() {
        }

        public float getTime() {
            return this.time;
        }

        public float getRotateMix() {
            return this.rotateMix;
        }

        public float getTranslateMix() {
            return this.translateMix;
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
