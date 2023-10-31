package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-16 10:59:58
 **/
@Getter
public class TransformTimeline extends CurveTimeline {
    
    private int transformIndex;
    
    private String transformName;
    
    private List<Frame> frameList;

    public TransformTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float rotateMix, float translateMix, float scaleMix, float shearMix) {
        this.frameList.add(new Frame(time, rotateMix, translateMix, scaleMix, shearMix));
    }

    public void setTransformIndex(int transformIndex) {
        this.transformIndex = transformIndex;
    }

    public void setTransformName(String transformName) {
        this.transformName = transformName;
    }

    public void setFrameList(List<Frame> frameList) {
        this.frameList = frameList;
    }

    @Getter
    public static class Frame {
        
        private float time;
        
        private float rotateMix;
        
        private float translateMix;
        
        private float scaleMix;
        
        private float shearMix;

        public Frame(float time, float rotateMix, float translateMix, float scaleMix, float shearMix) {
            this.time = time;
            this.rotateMix = rotateMix;
            this.translateMix = translateMix;
            this.scaleMix = scaleMix;
            this.shearMix = shearMix;
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

        public void setScaleMix(float scaleMix) {
            this.scaleMix = scaleMix;
        }

        public void setShearMix(float shearMix) {
            this.shearMix = shearMix;
        }
    }
}
