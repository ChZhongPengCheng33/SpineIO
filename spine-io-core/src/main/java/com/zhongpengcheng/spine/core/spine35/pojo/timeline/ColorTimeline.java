package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import com.google.gson.annotations.Expose;
import com.zhongpengcheng.spine.util.ColorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-01-26 21:13:00
 */
public class ColorTimeline extends CurveTimeline {
    private static final int ENTRIES = 5;
    private static final int PREV_TIME = -5, PREV_R = -4, PREV_G = -3, PREV_B = -2, PREV_A = -1;
    private static final int R = 1, G = 2, B = 3, A = 4;

    @Expose
    private int slotIndex;
    @Expose
    private String slotName;
    @Expose
    private List<Frame> frames;

    public ColorTimeline(int frameCount) {
        frames = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, ColorUtils.Color color) {
        frames.add(new Frame(time, color));
    }

    public int getSlotIndex() {
        return this.slotIndex;
    }

    public String getSlotName() {
        return this.slotName;
    }

    public List<Frame> getFrames() {
        return this.frames;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public static class Frame {
        @Expose
        private float time;
        @Expose
        private ColorUtils.Color color;

        Frame(float time, ColorUtils.Color color) {
            this.time = time;
            this.color = color;
        }

        public float getTime() {
            return this.time;
        }

        public ColorUtils.Color getColor() {
            return this.color;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public void setColor(ColorUtils.Color color) {
            this.color = color;
        }
    }
}
