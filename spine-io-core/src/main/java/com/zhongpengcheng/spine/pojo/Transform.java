package com.zhongpengcheng.spine.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author ZhongPengCheng
 * @since 2021-03-02 17:08:00
 */
@Data
@Builder
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
}
