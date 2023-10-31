package com.zhongpengcheng.spine.core.spine35v2.model;

import com.zhongpengcheng.spine.core.common.model.Color;
import com.zhongpengcheng.spine.core.spine35v2.enums.TransformModeEnum;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 骨骼对象
 *
 * @author zhongpengcheng
 * @since 2023-10-31 16:08:16
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Bone {
    /**
     * 骨骼名称. 该参数在一个skeleton中是唯一的.
     */
    private String name;
    /**
     * 骨骼顺序，从0开始
     * 自添加属性
     */
    private Integer order;
    /**
     * 父骨骼的索引加1. 根骨骼的该参数是忽略的.
     */
    private Integer parentIndex;
    /**
     * 在setup pose中, 该骨骼相对于父骨骼的旋转角度.
     */
    private Float rotation;
    /**
     * 在setup pose中骨骼相对于父骨骼坐标的X分量.
     */
    private Float x;
    /**
     * 在setup pose中骨骼相对于父骨骼坐标的Y分量.
     */
    private Float y;
    /**
     * 在setup pose中骨骼在X方向的缩放值.
     */
    private Float scaleX;
    /**
     * 在setup pose中骨骼在Y方向的缩放值.
     */
    private Float scaleY;
    /**
     * 在setup pose中骨骼在X方向斜切角度.
     */
    private Float shearX;
    /**
     * 在setup pose中骨骼在Y方向斜切角度.
     */
    private Float shearY;
    /**
     * 骨骼长度. 除了2骨骼IK和为骨骼绘制调试线外, 骨骼长度这个参数在运行时不常使用.
     */
    private Float length;
    /**
     * 决定了该骨骼如何继承父骨骼的transform.
     */
    @Builder.Default
    private TransformModeEnum transformMode = TransformModeEnum.NORMAL;
    /**
     * 骨骼的颜色, 与Spine中一致. 非必要的参数.
     */
    private Color color;
}
