package com.zhongpengcheng.spine.core.spine35v2.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 骨骼对象
 *
 * @author zhongpengcheng
 * @since 2023-10-31 15:50:12
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Skeleton {
    /**
     * 文件头信息
     */
    private Head head;
    /**
     * 骨骼集合
     */
    private List<Bone> bones;
}
