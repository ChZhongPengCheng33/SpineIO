package com.zhongpengcheng.spine.core.spine35.pojo;

import lombok.Getter;

/**
 * Bone部分数据
 *
 * @author skyfire33
 * @since 2021-03-02 0:35:00
 */
@Getter
public class Bone {
    /**
     * 骨骼名称
     */
    private String name;
    /**
     * 骨骼排序
     */
    private Integer id;
    /**
     * 父骨骼名称
     */
    private String parent;
    /**
     * 骨骼相对于父骨骼的旋转度
     */
    private Float rotation;
    /**
     * 相对于父骨骼的x坐标
     */
    private Float x;
    /**
     * 相对于父骨骼的y坐标
     */
    private Float y;
    private Float scaleX;
    private Float scaleY;
    private Float shearX;
    private Float shearY;
    /**
     * 骨骼长度
     */
    private Float length;
    /**
     * 转换模式
     */
    private String transformMode;
    /**
     * 骨骼颜色
     * 非必须
     */
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
