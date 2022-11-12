package com.zhongpengcheng.spine.io.v35.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZhongPengCheng
 * @since 2021-03-02 18:05:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Path {
    private String name;
    private Integer order;
    private List<String> bones;
    /**
     * Slot
     */
    private String target;
    private String positionMode;
    private String spacingMode;
    private String rotateMode;
    /*default 0*/
    private Float rotation = 0F;
    private Float position = 0F;
    private Float spacing = 0F;
    /*default 1*/
    private Float rotateMix = 1F;
    private Float translateMix = 1F;
}
