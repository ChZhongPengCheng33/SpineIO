package com.zhongpengcheng.spine.io.pojo;

import com.google.gson.annotations.Expose;

/**
 * Bone部分数据
 *
 * @author skyfire33
 * @since 2021-03-02 0:35:00
 */
public class Bone {
    /**
     * 骨骼名称
     */
    @Expose
    private String name;
    /**
     * 骨骼排序
     */
    @Expose
    private Integer id;
    /**
     * 父骨骼名称
     */
    @Expose
    private String parent;
    /**
     * 骨骼相对于父骨骼的旋转度
     */
    @Expose
    private Float rotation;
    /**
     * 相对于父骨骼的x坐标
     */
    @Expose
    private Float x;
    /**
     * 相对于父骨骼的y坐标
     */
    @Expose
    private Float y;
    @Expose
    private Float scaleX;
    @Expose
    private Float scaleY;
    @Expose
    private Float shearX;
    @Expose
    private Float shearY;
    /**
     * 骨骼长度
     */
    @Expose
    private Float length;
    /**
     * 转换模式
     */
    @Expose
    private String transformMode;
    /**
     * 骨骼颜色
     * 非必须
     */
    @Expose
    private String color;

    public Bone(String name, Integer id, String parent, Float rotation, Float x, Float y, Float scaleX, Float scaleY, Float shearX, Float shearY, Float length, String transformMode, String color) {
        this.name = name;
        this.id = id;
        this.parent = parent;
        this.rotation = rotation;
        this.x = x;
        this.y = y;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.shearX = shearX;
        this.shearY = shearY;
        this.length = length;
        this.transformMode = transformMode;
        this.color = color;
    }

    public Bone() {
    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getParent() {
        return this.parent;
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

    public Float getShearX() {
        return this.shearX;
    }

    public Float getShearY() {
        return this.shearY;
    }

    public Float getLength() {
        return this.length;
    }

    public String getTransformMode() {
        return this.transformMode;
    }

    public String getColor() {
        return this.color;
    }

    public Bone setName(String name) {
        this.name = name;
        return this;
    }

    public Bone setId(Integer id) {
        this.id = id;
        return this;
    }

    public Bone setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public Bone setRotation(Float rotation) {
        this.rotation = rotation;
        return this;
    }

    public Bone setX(Float x) {
        this.x = x;
        return this;
    }

    public Bone setY(Float y) {
        this.y = y;
        return this;
    }

    public Bone setScaleX(Float scaleX) {
        this.scaleX = scaleX;
        return this;
    }

    public Bone setScaleY(Float scaleY) {
        this.scaleY = scaleY;
        return this;
    }

    public Bone setShearX(Float shearX) {
        this.shearX = shearX;
        return this;
    }

    public Bone setShearY(Float shearY) {
        this.shearY = shearY;
        return this;
    }

    public Bone setLength(Float length) {
        this.length = length;
        return this;
    }

    public Bone setTransformMode(String transformMode) {
        this.transformMode = transformMode;
        return this;
    }

    public Bone setColor(String color) {
        this.color = color;
        return this;
    }
}
