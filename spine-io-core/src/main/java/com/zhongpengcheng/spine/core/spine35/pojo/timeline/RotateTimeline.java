package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-15 16:20:38
 **/
@Getter
public class RotateTimeline extends CurveTimeline {
    
    private int boneIndex;
    
    private String boneName;
    
    private List<Frame> frameList;
    
    private String timelineType;

    public RotateTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float degrees) {
        this.frameList.add(new Frame(time, degrees));
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
        
        private float time;
        
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
