package com.zhongpengcheng.spine.io.v35.enums;

/**
 * @author ZhongPengCheng
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
