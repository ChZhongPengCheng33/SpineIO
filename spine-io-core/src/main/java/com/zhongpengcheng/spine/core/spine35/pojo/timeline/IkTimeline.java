package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-15 17:13:48
 **/
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

    public int getIkIndex() {
        return this.ikIndex;
    }

    public String getIkName() {
        return this.ikName;
    }

    public List<Frame> getFrameList() {
        return this.frameList;
    }

    public IkTimeline setIkIndex(int ikIndex) {
        this.ikIndex = ikIndex;
        return this;
    }

    public IkTimeline setIkName(String ikName) {
        this.ikName = ikName;
        return this;
    }

    public IkTimeline setFrameList(List<Frame> frameList) {
        this.frameList = frameList;
        return this;
    }

    public static class Frame {
        @Expose
        private float time;
        @Expose
        private float mix;
        @Expose
        private int bendDirection;

        public Frame(float time, float mix, int bendDirection) {
            this.time = time;
            this.mix = mix;
            this.bendDirection = bendDirection;
        }

        public Frame() {
        }

        public float getTime() {
            return this.time;
        }

        public float getMix() {
            return this.mix;
        }

        public int getBendDirection() {
            return this.bendDirection;
        }

        public Frame setTime(float time) {
            this.time = time;
            return this;
        }

        public Frame setMix(float mix) {
            this.mix = mix;
            return this;
        }

        public Frame setBendDirection(int bendDirection) {
            this.bendDirection = bendDirection;
            return this;
        }
    }
}
