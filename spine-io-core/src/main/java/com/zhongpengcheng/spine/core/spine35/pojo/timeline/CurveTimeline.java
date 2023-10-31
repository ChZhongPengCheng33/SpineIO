package com.zhongpengcheng.spine.core.spine35.pojo.timeline;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-01-28 13:31:00
 */
@Getter
public abstract class CurveTimeline implements ITimeline {
    /**
     * 线性曲线
     */
    public static final float LINEAR = 0;
    /**
     * 分段曲线
     */
    public static final float STEPPED = 1;
    /**
     * 贝塞尔曲线
     */
    public static final float BEZIER = 2;
    static public final int BEZIER_SIZE = 10 * 2 - 1;
    
    private float[] curves = new float[4];
    
    private boolean stepped = false;
    
    private List<float[]> curveList = new ArrayList<>();
    
    private List<Boolean> steppedList = new ArrayList<>();
    
    private List<Integer> frameIndexList = new ArrayList<>();

    public void setCurve(float... curves) {
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

    public void setCurves(float[] curves) {
        this.curves = curves;
    }

    public void setCurveList(List<float[]> curveList) {
        this.curveList = curveList;
    }

    public void setSteppedList(List<Boolean> steppedList) {
        this.steppedList = steppedList;
    }

    public void setFrameIndexList(List<Integer> frameIndexList) {
        this.frameIndexList = frameIndexList;
    }
}
