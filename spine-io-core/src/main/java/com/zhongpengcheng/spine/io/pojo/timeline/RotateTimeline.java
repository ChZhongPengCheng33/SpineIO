package com.zhongpengcheng.spine.io.pojo.timeline;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-15 16:20:38
 **/
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
        this.frameList.add(new Frame(time, degrees));
    }

    public int getBoneIndex() {
        return this.boneIndex;
    }

    public String getBoneName() {
        return this.boneName;
    }

    public List<Frame> getFrameList() {
        return this.frameList;
    }

    public String getTimelineType() {
        return this.timelineType;
    }

    public void setBoneIndex(int boneIndex) {
        this.boneIndex = boneIndex;
    }

    public void setBoneName(String boneName) {
        this.boneName = boneName;
    }

    public void setFrameList(List<Frame> frameList) {
        this.frameList = frameList;
    }

    public void setTimelineType(String timelineType) {
        this.timelineType = timelineType;
    }

    public static class Frame {
        @Expose
        private float time;
        @Expose
        private float degrees;

        Frame(float time, float degrees) {
            this.time = time;
            this.degrees = degrees;
        }

        public float getTime() {
            return this.time;
        }

        public float getDegrees() {
            return this.degrees;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public void setDegrees(float degrees) {
            this.degrees = degrees;
        }
    }
}
