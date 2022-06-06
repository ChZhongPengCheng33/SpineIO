package com.zhongpengcheng.spine.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 骨骼文件头信息
 * @author ZhongPengCheng
 * @since 2021-03-01 23:20:00
 */
@Data
@Builder
@EqualsAndHashCode(of = "hash")
public class Head {
    /**
     * 骨骼文件哈希值，计算方式未知
     */
    private String hash;
    /**
     * 导出骨骼文件的Spine版本
     */
    private String version;
    /**
     * 骨骼中attachments的的宽度
     */
    private Float width;
    /**
     * 骨骼中attachments的的高度
     */
    private Float height;
    /**
     * 如果为假，将省略标记为非必要的数据。
     */
    @Builder.Default
    private Boolean nonessential = Boolean.FALSE;
    /**
     * 骨骼动画的帧数
     * 非必须
     */
    private Float fps;
    /**
     * 贴图文件路径
     * 非必须
     */
    private String images;
}
