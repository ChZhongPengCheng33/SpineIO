package com.zhongpengcheng.spine.io.enums;

/**
 * @author skyfire33
 * @since 2021-03-02 18:37:00
 */
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

    public String getCode() {
        return code;
    }
}
