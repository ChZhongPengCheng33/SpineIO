package com.zhongpengcheng.spine.core.spine35.pojo.attachment;

import com.zhongpengcheng.spine.core.spine35.enums.AttachmentType;
import lombok.Getter;

/**
 * @author skyfire33
 * @since 2021-03-02 18:48:00
 */
@Getter
public class RegionAttachment implements IAttachment {
    private String slot;
    private String type = AttachmentType.REGION.getCode();
    private String name;
    private String path;
    /**
     * default 0
     */
    private Float rotation;
    /**
     * default 0
     */
    private Float x;
    /**
     * default 0
     */
    private Float y;
    /**
     * default 1
     */
    private Float scaleX;
    /**
     * default 1
     */
    private Float scaleY;
    /**
     * not null
     */
    private Float width;
    /**
     * not null
     */
    private Float height;
    /**
     * default null
     */
    private String color;
    private int slotIndex;

    public RegionAttachment(String slot, String type, String name, String path, Float rotation, Float x, Float y, Float scaleX, Float scaleY, Float width, Float height, String color, int slotIndex) {
        this.slot = slot;
        this.type = type;
        this.name = name;
        this.path = path;
        this.rotation = rotation;
        this.x = x;
        this.y = y;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.slotIndex = slotIndex;
    }

    public RegionAttachment() {
    }

    @Override
    public String getAttachmentName() {
        return this.name;
    }

    public RegionAttachment setSlot(String slot) {
        this.slot = slot;
        return this;
    }

    public RegionAttachment setType(String type) {
        this.type = type;
        return this;
    }

    public RegionAttachment setName(String name) {
        this.name = name;
        return this;
    }

    public RegionAttachment setPath(String path) {
        this.path = path;
        return this;
    }

    public RegionAttachment setRotation(Float rotation) {
        this.rotation = rotation;
        return this;
    }

    public RegionAttachment setX(Float x) {
        this.x = x;
        return this;
    }

    public RegionAttachment setY(Float y) {
        this.y = y;
        return this;
    }

    public RegionAttachment setScaleX(Float scaleX) {
        this.scaleX = scaleX;
        return this;
    }

    public RegionAttachment setScaleY(Float scaleY) {
        this.scaleY = scaleY;
        return this;
    }

    public RegionAttachment setWidth(Float width) {
        this.width = width;
        return this;
    }

    public RegionAttachment setHeight(Float height) {
        this.height = height;
        return this;
    }

    public RegionAttachment setColor(String color) {
        this.color = color;
        return this;
    }

    public RegionAttachment setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
        return this;
    }
}
