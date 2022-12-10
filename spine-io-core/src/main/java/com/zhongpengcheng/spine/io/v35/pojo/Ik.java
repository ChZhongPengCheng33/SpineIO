package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 约束
 *
 * @author ZhongPengCheng
 * @since 2021-03-02 2:03:00
 */
@Builder
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Ik {
    @Expose
    private String name;
    @Expose
    private Integer order;
    @Expose
    private List<String> bones;
    @Expose
    private String target;
    @Expose
    private Float mix = 1F;
    @Expose
    private Integer bendPositive = 1;
}
