package com.zhongpengcheng.spine.io.pojo;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author skyfire33
 * @since 2022-12-10 18:40:00
 */
public class Transform {
    @Expose
    private String name;
    @Expose
    private Integer order;
    @Expose
    private List<String> bones;
    @Expose
    private String target;
    /*以下默认值值0*/
    @Expose
    private Float rotation = 0F;
    @Expose
    private Float x = 0F;
    @Expose
    private Float y = 0F;
    @Expose
    private Float scaleX = 0F;
    @Expose
    private Float scaleY = 0F;
    @Expose
    private Float shearY = 0F;
    /*以下默认值值1*/
    @Expose
    private Float rotateMix = 1F;
    @Expose
    private Float translateMix = 1F;
    @Expose
    private Float scaleMix = 1F;
    @Expose
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

    public Float getRotation() {
        return this.rotation;
    }

    public Float getX() {
        return this.x;
    }

    public Float getY() {
        return this.y;
    }

    public Float getScaleX() {
        return this.scaleX;
    }

    public Float getScaleY() {
        return this.scaleY;
    }

    public Float getShearY() {
        return this.shearY;
    }

    public Float getRotateMix() {
        return this.rotateMix;
    }

    public Float getTranslateMix() {
        return this.translateMix;
    }

    public Float getScaleMix() {
        return this.scaleMix;
    }

    public Float getShearMix() {
        return this.shearMix;
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
