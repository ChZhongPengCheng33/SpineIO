package com.zhongpengcheng.spine.io.v35.pojo.attachment;

import com.zhongpengcheng.spine.io.v35.enums.AttachmentType;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author ZhongPengCheng
 * @since 2021-03-02 18:48:00
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegionAttachment implements IAttachment {
    private String slot;
    @Builder.Default
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

    @Override
    public String getAttachmentName() {
        return this.name;
    }
}
