package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhongPengCheng
 * @since 2022-01-28 13:31:00
 */
@Getter
@Setter
public abstract class CurveTimeline implements ITimeline {
    static public final float LINEAR = 0, STEPPED = 1, BEZIER = 2;
    static public final int BEZIER_SIZE = 10 * 2 - 1;
    @Expose
    private float[] curves = new float[4];
    @Expose
    private boolean stepped = false;
    @Expose
    private List<float[]> curveList = new ArrayList<>();
    @Expose
    private List<Boolean> steppedList = new ArrayList<>();
    @Expose
    private List<Integer> frameIndexList = new ArrayList<>();

    public void setCurve (float...curves) {
        this.curves = curves;
        this.stepped = false;

        curveList.add(curves);
        steppedList.add(false);
    }

    public void setStepped(boolean stepped) {
        this.stepped = stepped;

        curveList.add(new float[4]);
        steppedList.add(true);
    }

    public void addFrameIndex(Integer frameIndex) {
        frameIndexList.add(frameIndex);
    }
}
