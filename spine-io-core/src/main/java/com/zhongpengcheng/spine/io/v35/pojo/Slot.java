package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 插槽
 * @author ZhongPengCheng
 * @since 2021-03-02 1:20:00
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Slot {
    /**
     * 插槽名称
     */
    @Expose
    private String name;
    /**
     * 插槽编号
     */
    @Expose
    private Integer id;
    /**
     * 插槽对应骨骼信息
     */
    @Expose
    private String bone;
    /**
     * 插槽附件名称
     */
    @Expose
    private String attachment;
    /**
     * 插槽颜色
     */
    @Expose
    private String color;
    @Expose
    private String blend;
}
