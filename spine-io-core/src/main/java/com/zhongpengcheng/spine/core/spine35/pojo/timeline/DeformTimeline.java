package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-16 14:47:15
 **/
public class DeformTimeline extends CurveTimeline {
    @Expose
    private int slotIndex;
    @Expose
    private int skinIndex;
    @Expose
    private String slotName;
    @Expose
    private String skinName;
    @Expose
    private String attachmentName;
    @Expose
    private List<Frame> frameList;

    public DeformTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float[] vertices) {
        this.frameList.add(new Frame(time, vertices));
    }

    public int getSlotIndex() {
        return this.slotIndex;
    }

    public int getSkinIndex() {
        return this.skinIndex;
    }

    public String getSlotName() {
        return this.slotName;
    }

    public String getSkinName() {
        return this.skinName;
    }

    public String getAttachmentName() {
        return this.attachmentName;
    }

    public List<Frame> getFrameList() {
        return this.frameList;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    public void setSkinIndex(int skinIndex) {
        this.skinIndex = skinIndex;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public void setFrameList(List<Frame> frameList) {
        this.frameList = frameList;
    }

    public static class Frame {
        @Expose
        private float time;
        @Expose
        private float[] vertices;

        public Frame(float time, float[] vertices) {
            this.time = time;
            this.vertices = vertices;
        }

        public Frame() {
        }

        public float getTime() {
            return this.time;
        }

        public float[] getVertices() {
            return this.vertices;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public void setVertices(float[] vertices) {
            this.vertices = vertices;
        }
    }
}
