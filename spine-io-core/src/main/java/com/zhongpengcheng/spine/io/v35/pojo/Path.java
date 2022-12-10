package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author ZhongPengCheng
 * @since 2021-03-02 18:05:00
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
