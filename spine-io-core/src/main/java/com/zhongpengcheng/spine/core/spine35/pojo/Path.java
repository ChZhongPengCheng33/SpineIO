package com.zhongpengcheng.spine.core.spine35.pojo;

import lombok.Getter;

import java.util.List;

/**
 * @author skyfire33
 * @since 2021-03-02 18:05:00
 */
@Getter
public class Path {
    
    private String name;
    
    private Integer order;
    
    private List<String> bones;
    /**
     * Slot
     */
    
    private String target;
    
    private String positionMode;
    
    private String spacingMode;
    
    private String rotateMode;
    /*default 0*/
    
    private Float rotation = 0F;
    
    private Float position = 0F;
    
    private Float spacing = 0F;
    /*default 1*/
    
    private Float rotateMix = 1F;
    
    private Float translateMix = 1F;

    public Path(String name, Integer order, List<String> bones, String target, String positionMode, String spacingMode, String rotateMode, Float rotation, Float position, Float spacing, Float rotateMix, Float translateMix) {
        this.name = name;
        this.order = order;
        this.bones = bones;
        this.target = target;
        this.positionMode = positionMode;
        this.spacingMode = spacingMode;
        this.rotateMode = rotateMode;
        this.rotation = rotation;
        this.position = position;
        this.spacing = spacing;
        this.rotateMix = rotateMix;
        this.translateMix = translateMix;
    }

    public Path() {
    }

    public Path setName(String name) {
        this.name = name;
        return this;
    }

    public Path setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public Path setBones(List<String> bones) {
        this.bones = bones;
        return this;
    }

    public Path setTarget(String target) {
        this.target = target;
        return this;
    }

    public Path setPositionMode(String positionMode) {
        this.positionMode = positionMode;
        return this;
    }

    public Path setSpacingMode(String spacingMode) {
        this.spacingMode = spacingMode;
        return this;
    }

    public Path setRotateMode(String rotateMode) {
        this.rotateMode = rotateMode;
        return this;
    }

    public Path setRotation(Float rotation) {
        this.rotation = rotation;
        return this;
    }

    public Path setPosition(Float position) {
        this.position = position;
        return this;
    }

    public Path setSpacing(Float spacing) {
        this.spacing = spacing;
        return this;
    }

    public Path setRotateMix(Float rotateMix) {
        this.rotateMix = rotateMix;
        return this;
    }

    public Path setTranslateMix(Float translateMix) {
        this.translateMix = translateMix;
        return this;
    }
}
