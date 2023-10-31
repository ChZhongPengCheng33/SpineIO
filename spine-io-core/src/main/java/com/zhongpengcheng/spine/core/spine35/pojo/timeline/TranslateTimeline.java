package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-15 16:31:16
 **/
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
        Frame frame = new Frame()
                .setTime(time)
                .setX(x)
                .setY(y);
        this.frameList.add(frame);
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
        private float x;
        @Expose
        private float y;

        public Frame(float time, float x, float y) {
            this.time = time;
            this.x = x;
            this.y = y;
        }

        public Frame() {
        }

        public static FrameBuilder builder() {
            return new FrameBuilder();
        }

        public float getTime() {
            return this.time;
        }

        public float getX() {
            return this.x;
        }

        public float getY() {
            return this.y;
        }

        public Frame setTime(float time) {
            this.time = time;
            return this;
        }

        public Frame setX(float x) {
            this.x = x;
            return this;
        }

        public Frame setY(float y) {
            this.y = y;
            return this;
        }

        public static class FrameBuilder {
            private float time;
            private float x;
            private float y;

            FrameBuilder() {
            }

            public FrameBuilder time(float time) {
                this.time = time;
                return this;
            }

            public FrameBuilder x(float x) {
                this.x = x;
                return this;
            }

            public FrameBuilder y(float y) {
                this.y = y;
                return this;
            }

            public Frame build() {
                return new Frame(time, x, y);
            }

            public String toString() {
                return "TranslateTimeline.Frame.FrameBuilder(time=" + this.time + ", x=" + this.x + ", y=" + this.y + ")";
            }
        }
    }
}
