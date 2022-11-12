package com.zhongpengcheng.spine.io.v35.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 约束
 *
 * @author ZhongPengCheng
 * @since 2021-03-02 2:03:00
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ik {
    private String name;
    private Integer order;
    private List<String> bones;
    private String target;
    private Float mix = 1F;
    private Integer bendPositive = 1;
}
