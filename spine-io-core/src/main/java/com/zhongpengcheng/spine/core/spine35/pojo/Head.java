package com.zhongpengcheng.spine.core.spine35.pojo;

import lombok.Getter;

/**
 * 骨骼文件头信息
 *
 * @author skyfire33
 * @since 2021-03-01 23:20:00
 */
@Getter
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

    public Head setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public Head setVersion(String version) {
        this.version = version;
        return this;
    }

    public Head setWidth(Float width) {
        this.width = width;
        return this;
    }

    public Head setHeight(Float height) {
        this.height = height;
        return this;
    }

    public Head setNonessential(Boolean nonessential) {
        this.nonessential = nonessential;
        return this;
    }

    public Head setFps(Float fps) {
        this.fps = fps;
        return this;
    }

    public Head setImages(String images) {
        this.images = images;
        return this;
    }
}
