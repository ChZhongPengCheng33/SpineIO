package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;

/**
 * 骨骼文件头信息
 * @author ZhongPengCheng
 * @since 2021-03-01 23:20:00
 */
public class Head {
    /**
     * 骨骼文件哈希值，计算方式未知
     */
    @Expose
    private String hash;
    /**
     * 导出骨骼文件的Spine版本
     */
    @Expose
    private String version;
    /**
     * 骨骼中attachments的的宽度
     */
    @Expose
    private Float width;
    /**
     * 骨骼中attachments的的高度
     */
    @Expose
    private Float height;
    /**
     * 如果为假，将省略标记为非必要的数据。
     */
    @Expose
    private Boolean nonessential = Boolean.FALSE;
    /**
     * 骨骼动画的帧数
     * 非必须
     */
    @Expose
    private Float fps;
    /**
     * 贴图文件路径
     * 非必须
     */
    @Expose
    private String images;

    public String getHash() {
        return this.hash;
    }

    public String getVersion() {
        return this.version;
    }

    public Float getWidth() {
        return this.width;
    }

    public Float getHeight() {
        return this.height;
    }

    public Boolean getNonessential() {
        return this.nonessential;
    }

    public Float getFps() {
        return this.fps;
    }

    public String getImages() {
        return this.images;
    }

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
