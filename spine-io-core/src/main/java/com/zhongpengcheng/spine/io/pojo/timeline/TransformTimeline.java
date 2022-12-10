package com.zhongpengcheng.spine.io.pojo.timeline;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-16 10:59:58
 **/
public class TransformTimeline extends CurveTimeline {
    @Expose
    private int transformIndex;
    @Expose
    private String transformName;
    @Expose
    private List<Frame> frameList;

    public TransformTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float rotateMix, float translateMix, float scaleMix, float shearMix) {
        this.frameList.add(new Frame(time, rotateMix, translateMix, scaleMix, shearMix));
    }

    public int getTransformIndex() {
        return this.transformIndex;
    }

    public String getTransformName() {
        return this.transformName;
    }

    public List<Frame> getFrameList() {
        return this.frameList;
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

    public static class Frame {
        @Expose
        private float time;
        @Expose
        private float rotateMix;
        @Expose
        private float translateMix;
        @Expose
        private float scaleMix;
        @Expose
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

        public float getTime() {
            return this.time;
        }

        public float getRotateMix() {
            return this.rotateMix;
        }

        public float getTranslateMix() {
            return this.translateMix;
        }

        public float getScaleMix() {
            return this.scaleMix;
        }

        public float getShearMix() {
            return this.shearMix;
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
