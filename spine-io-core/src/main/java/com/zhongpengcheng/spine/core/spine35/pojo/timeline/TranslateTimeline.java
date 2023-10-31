package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-15 16:31:16
 **/
@Getter
public class TranslateTimeline extends CurveTimeline {
    
    private int boneIndex;
    
    private String boneName;
    
    private List<Frame> frameList;
    
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

    @Getter
    public static class Frame {
        
        private float time;
        
        private float x;
        
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
