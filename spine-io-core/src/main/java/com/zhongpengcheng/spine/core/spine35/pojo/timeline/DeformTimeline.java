package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-16 14:47:15
 **/
@Getter
public class DeformTimeline extends CurveTimeline {
    
    private int slotIndex;
    
    private int skinIndex;
    
    private String slotName;
    
    private String skinName;
    
    private String attachmentName;
    
    private List<Frame> frameList;

    public DeformTimeline(int frameCount) {
        this.frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, float[] vertices) {
        this.frameList.add(new Frame(time, vertices));
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

    @Getter
    public static class Frame {
        
        private float time;
        
        private float[] vertices;

        public Frame(float time, float[] vertices) {
            this.time = time;
            this.vertices = vertices;
        }

        public Frame() {
        }

        public void setTime(float time) {
            this.time = time;
        }

        public void setVertices(float[] vertices) {
            this.vertices = vertices;
        }
    }
}
