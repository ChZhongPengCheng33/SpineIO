package com.zhongpengcheng.spine.core.spine35.pojo;

import lombok.Getter;

import java.util.List;

/**
 * 逆运动约束
 *
 * @author skyfire33
 * @since 2021-03-02 2:03:00
 */
@Getter
public class Ik {
    
    private String name;
    
    private Integer order;
    
    private List<String> bones;
    
    private String target;
    
    private Float mix = 1F;
    
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
