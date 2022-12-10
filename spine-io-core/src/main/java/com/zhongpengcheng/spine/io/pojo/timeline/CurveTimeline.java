package com.zhongpengcheng.spine.io.pojo.timeline;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyfire33
 * @since 2022-01-28 13:31:00
 */
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

    public float[] getCurves() {
        return this.curves;
    }

    public boolean isStepped() {
        return this.stepped;
    }

    public List<float[]> getCurveList() {
        return this.curveList;
    }

    public List<Boolean> getSteppedList() {
        return this.steppedList;
    }

    public List<Integer> getFrameIndexList() {
        return this.frameIndexList;
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
