package com.zhongpengcheng.spine.core.spine35.pojo;

import lombok.Getter;

import java.util.List;

/**
 * @author skyfire33
 * @since 2022-12-10 18:40:00
 */
@Getter
public class Transform {
    
    private String name;
    
    private Integer order;
    
    private List<String> bones;
    
    private String target;
    /*以下默认值值0*/
    
    private Float rotation = 0F;
    
    private Float x = 0F;
    
    private Float y = 0F;
    
    private Float scaleX = 0F;
    
    private Float scaleY = 0F;
    
    private Float shearY = 0F;
    /*以下默认值值1*/
    
    private Float rotateMix = 1F;
    
    private Float translateMix = 1F;
    
    private Float scaleMix = 1F;
    
    private Float shearMix = 1F;

    public Transform(String name, Integer order, List<String> bones, String target, Float rotation, Float x, Float y, Float scaleX, Float scaleY, Float shearY, Float rotateMix, Float translateMix, Float scaleMix, Float shearMix) {
        this.name = name;
        this.order = order;
        this.bones = bones;
        this.target = target;
        this.rotation = rotation;
        this.x = x;
        this.y = y;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.shearY = shearY;
        this.rotateMix = rotateMix;
        this.translateMix = translateMix;
        this.scaleMix = scaleMix;
        this.shearMix = shearMix;
    }

    public Transform() {
    }

    public Transform setName(String name) {
        this.name = name;
        return this;
    }

    public Transform setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public Transform setBones(List<String> bones) {
        this.bones = bones;
        return this;
    }

    public Transform setTarget(String target) {
        this.target = target;
        return this;
    }

    public Transform setRotation(Float rotation) {
        this.rotation = rotation;
        return this;
    }

    public Transform setX(Float x) {
        this.x = x;
        return this;
    }

    public Transform setY(Float y) {
        this.y = y;
        return this;
    }

    public Transform setScaleX(Float scaleX) {
        this.scaleX = scaleX;
        return this;
    }

    public Transform setScaleY(Float scaleY) {
        this.scaleY = scaleY;
        return this;
    }

    public Transform setShearY(Float shearY) {
        this.shearY = shearY;
        return this;
    }

    public Transform setRotateMix(Float rotateMix) {
        this.rotateMix = rotateMix;
        return this;
    }

    public Transform setTranslateMix(Float translateMix) {
        this.translateMix = translateMix;
        return this;
    }

    public Transform setScaleMix(Float scaleMix) {
        this.scaleMix = scaleMix;
        return this;
    }

    public Transform setShearMix(Float shearMix) {
        this.shearMix = shearMix;
        return this;
    }
}
