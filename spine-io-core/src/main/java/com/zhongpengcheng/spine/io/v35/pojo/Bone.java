package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Bone部分数据
 * @author ZhongPengCheng
 * @since 2021-03-02 0:35:00
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
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
}
