package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author ZhongPengCheng
 * @since 2021-03-02 17:08:00
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
