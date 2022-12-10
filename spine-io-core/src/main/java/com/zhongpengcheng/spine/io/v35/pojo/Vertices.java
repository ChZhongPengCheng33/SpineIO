package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author ZhongPengCheng
 * @since 2021-03-02 18:50:00
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class Vertices {
    @Expose
    private Integer[] bones;

    @Expose private Float[] vertices;
}
