package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-02-16 15:31:31
 **/
public class DrawOrderTimeline implements ITimeline {
    @Expose
    private List<Frame> frameList;

    public DrawOrderTimeline(int frameCount) {
        frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, List<Offset> offsets) {
        this.frameList.add(new Frame(time, offsets));
    }

    public List<Frame> getFrameList() {
        return this.frameList;
    }

    public void setFrameList(List<Frame> frameList) {
        this.frameList = frameList;
    }

    public static class Frame {
        @Expose
        private float time;
        @Expose
        private List<Offset> offsets;

        public Frame(float time, List<Offset> offsets) {
            this.time = time;
            this.offsets = offsets;
        }

        public Frame() {
        }

        public float getTime() {
            return this.time;
        }

        public List<Offset> getOffsets() {
            return this.offsets;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public void setOffsets(List<Offset> offsets) {
            this.offsets = offsets;
        }
    }

    public static class Offset {
        @Expose
        private int slotIndex;
        @Expose
        private int offset;
        @Expose
        private String slotName;

        public Offset(int slotIndex, int offset, String slotName) {
            this.slotIndex = slotIndex;
            this.offset = offset;
            this.slotName = slotName;
        }

        public Offset() {
        }

        public int getSlotIndex() {
            return this.slotIndex;
        }

        public int getOffset() {
            return this.offset;
        }

        public String getSlotName() {
            return this.slotName;
        }

        public void setSlotIndex(int slotIndex) {
            this.slotIndex = slotIndex;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public void setSlotName(String slotName) {
            this.slotName = slotName;
        }
    }
}
