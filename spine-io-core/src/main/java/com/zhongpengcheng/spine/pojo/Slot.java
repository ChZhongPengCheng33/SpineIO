package com.zhongpengcheng.spine.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * 插槽
 * @author ZhongPengCheng
 * @since 2021-03-02 1:20:00
 */
@Data
@Builder
public class Slot {
    /**
     * 插槽名称
     */
    private String name;
    /**
     * 插槽编号
     */
    private Integer id;
    /**
     * 插槽对应骨骼信息
     */
    private String bone;
    /**
     * 插槽附件名称
     */
    private String attachment;
    /**
     * 插槽颜色
     */
    private String color;
    private String blend;
}
