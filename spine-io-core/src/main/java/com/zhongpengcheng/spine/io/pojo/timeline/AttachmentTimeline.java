package com.zhongpengcheng.spine.io.pojo.timeline;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-01-26 21:10:00
 */
public class AttachmentTimeline implements ITimeline {
    @Expose
    private int slotIndex;
    @Expose
    private String slotName;
    @Expose
    private List<Frame> frameList;

    public AttachmentTimeline(int frameCount) {
        frameList = new ArrayList<>(frameCount);
    }

    public AttachmentTimeline(int slotIndex, String slotName, List<Frame> frameList) {
        this.slotIndex = slotIndex;
        this.slotName = slotName;
        this.frameList = frameList;
    }

    public AttachmentTimeline() {
    }

    public void addFrame(float time, String attachmentName) {
        frameList.add(Frame.of(time, attachmentName));
    }

    public int getSlotIndex() {
        return this.slotIndex;
    }

    public String getSlotName() {
        return this.slotName;
    }

    public List<Frame> getFrameList() {
        return this.frameList;
    }

    public AttachmentTimeline setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
        return this;
    }

    public AttachmentTimeline setSlotName(String slotName) {
        this.slotName = slotName;
        return this;
    }

    public AttachmentTimeline setFrameList(List<Frame> frameList) {
        this.frameList = frameList;
        return this;
    }

    public static class Frame {
        @Expose
        private float time;
        @Expose
        private String name;

        Frame(float time, String name) {
            this.time = time;
            this.name = name;
        }

        public static Frame of(float time, String name) {
            return new Frame(time, name);
        }

        public float getTime() {
            return this.time;
        }

        public String getName() {
            return this.name;
        }

        public Frame setTime(float time) {
            this.time = time;
            return this;
        }

        public Frame setName(String name) {
            this.name = name;
            return this;
        }
    }
}
