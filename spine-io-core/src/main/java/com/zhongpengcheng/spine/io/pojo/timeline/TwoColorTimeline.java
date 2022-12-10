package com.zhongpengcheng.spine.io.pojo.timeline;

import com.google.gson.annotations.Expose;
import com.zhongpengcheng.spine.util.ColorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-01-28 13:35:00
 */
public class TwoColorTimeline extends CurveTimeline {
    static public final int ENTRIES = 8;
    static private final int PREV_TIME = -8, PREV_R = -7, PREV_G = -6, PREV_B = -5, PREV_A = -4;
    static private final int PREV_R2 = -3, PREV_G2 = -2, PREV_B2 = -1;
    static private final int R = 1, G = 2, B = 3, A = 4, R2 = 5, G2 = 6, B2 = 7;

    @Expose
    private int slotIndex;
    @Expose
    private String slotName;
    @Expose
    private List<Frame> frames;

    public TwoColorTimeline(int frameCount) {
        frames = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, ColorUtils.Color light, ColorUtils.Color dark) {
        frames.add(new Frame(time, light, dark));
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
        private ColorUtils.Color light;
        @Expose
        private ColorUtils.Color dark;

        public Frame(float time, ColorUtils.Color light, ColorUtils.Color dark) {
            this.time = time;
            this.light = light;
            this.dark = dark;
        }

        public Frame() {
        }

        public float getTime() {
            return this.time;
        }

        public ColorUtils.Color getLight() {
            return this.light;
        }

        public ColorUtils.Color getDark() {
            return this.dark;
        }

        public Frame setTime(float time) {
            this.time = time;
            return this;
        }

        public Frame setLight(ColorUtils.Color light) {
            this.light = light;
            return this;
        }

        public Frame setDark(ColorUtils.Color dark) {
            this.dark = dark;
            return this;
        }
    }
}
