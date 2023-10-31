package com.zhongpengcheng.spine.core.spine35.pojo;

import com.google.gson.annotations.Expose;

/**
 * 插槽
 *
 * @author skyfire33
 * @since 2021-03-02 1:20:00
 */
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

    public Slot(String name, Integer id, String bone, String attachment, String color, String blend) {
        this.name = name;
        this.id = id;
        this.bone = bone;
        this.attachment = attachment;
        this.color = color;
        this.blend = blend;
    }

    public Slot() {
    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getBone() {
        return this.bone;
    }

    public String getAttachment() {
        return this.attachment;
    }

    public String getColor() {
        return this.color;
    }

    public String getBlend() {
        return this.blend;
    }

    public Slot setName(String name) {
        this.name = name;
        return this;
    }

    public Slot setId(Integer id) {
        this.id = id;
        return this;
    }

    public Slot setBone(String bone) {
        this.bone = bone;
        return this;
    }

    public Slot setAttachment(String attachment) {
        this.attachment = attachment;
        return this;
    }

    public Slot setColor(String color) {
        this.color = color;
        return this;
    }

    public Slot setBlend(String blend) {
        this.blend = blend;
        return this;
    }
}
