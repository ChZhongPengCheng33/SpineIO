package com.zhongpengcheng.spine.enums;

import lombok.Getter;

/**
 * @author ZhongPengCheng
 * @since 2021-03-02 18:37:00
 */
@Getter
public enum AttachmentType {
    REGION("region"),
    BOUNDING_BOX("boundingbox"),
    MESH("mesh"),
    LINKED_MESH("linkedmesh"),
    PATH("path");

    private final String code;

    AttachmentType(String code) {
        this.code = code;
    }
}
