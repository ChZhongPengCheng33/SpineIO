package com.zhongpengcheng.spine.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author ZhongPengCheng
 * @since 2021-03-02 18:50:00
 */
@Data
@Builder
public class Vertices {
    private Integer[] bones;
    private Float[] vertices;
}
