package com.zhongpengcheng.spine.io.pojo;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author skyfire33
 * @since 2021-03-02 18:05:00
 */
public class Path {
    @Expose
    private String name;
    @Expose
    private Integer order;
    @Expose
    private List<String> bones;
    /**
     * Slot
     */
    @Expose
    private String target;
    @Expose
    private String positionMode;
    @Expose
    private String spacingMode;
    @Expose
    private String rotateMode;
    /*default 0*/
    @Expose
    private Float rotation = 0F;
    @Expose
    private Float position = 0F;
    @Expose
    private Float spacing = 0F;
    /*default 1*/
    @Expose
    private Float rotateMix = 1F;
    @Expose
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

    public String getPositionMode() {
        return this.positionMode;
    }

    public String getSpacingMode() {
        return this.spacingMode;
    }

    public String getRotateMode() {
        return this.rotateMode;
    }

    public Float getRotation() {
        return this.rotation;
    }

    public Float getPosition() {
        return this.position;
    }

    public Float getSpacing() {
        return this.spacing;
    }

    public Float getRotateMix() {
        return this.rotateMix;
    }

    public Float getTranslateMix() {
        return this.translateMix;
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
