package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import com.zhongpengcheng.spine.util.ColorUtils;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhongPengCheng
 * @since 2022-01-26 21:13:00
 */
@Getter
@Setter
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

    @Data
    @Builder
    public static class Frame {
        @Expose
        private float time;
        @Expose
        private ColorUtils.Color color;
    }
}
