package com.zhongpengcheng.spine.core.spine35.pojo;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 约束
 *
 * @author skyfire33
 * @since 2021-03-02 2:03:00
 */
public class Ik {
    @Expose
    private String name;
    @Expose
    private Integer order;
    @Expose
    private List<String> bones;
    @Expose
    private String target;
    @Expose
    private Float mix = 1F;
    @Expose
    private Integer bendPositive = 1;

    public Ik(String name, Integer order, List<String> bones, String target, Float mix, Integer bendPositive) {
        this.name = name;
        this.order = order;
        this.bones = bones;
        this.target = target;
        this.mix = mix;
        this.bendPositive = bendPositive;
    }

    public Ik() {
    }

    public String getName() {
        return this.name;
    }

    public Integer getOrder() {
        return this.order;
    }

    public List<String> getBones() {
        return this.bones;
    }

    public String getTarget() {
        return this.target;
    }

    public Float getMix() {
        return this.mix;
    }

    public Integer getBendPositive() {
        return this.bendPositive;
    }

    public Ik setName(String name) {
        this.name = name;
        return this;
    }

    public Ik setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public Ik setBones(List<String> bones) {
        this.bones = bones;
        return this;
    }

    public Ik setTarget(String target) {
        this.target = target;
        return this;
    }

    public Ik setMix(Float mix) {
        this.mix = mix;
        return this;
    }

    public Ik setBendPositive(Integer bendPositive) {
        this.bendPositive = bendPositive;
        return this;
    }
}
