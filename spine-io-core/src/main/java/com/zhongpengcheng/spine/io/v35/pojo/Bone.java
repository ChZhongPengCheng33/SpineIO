package com.zhongpengcheng.spine.io.v35.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bone部分数据
 * @author ZhongPengCheng
 * @since 2021-03-02 0:35:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
