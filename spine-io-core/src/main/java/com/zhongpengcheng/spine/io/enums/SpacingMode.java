package com.zhongpengcheng.spine.io.enums;

/**
 * @author skyfire33
 * @since 2021-03-02 18:18:00
 */
public enum SpacingMode {
    LENGTH("length", "长度"),
    FIXED("fixed", "固定值"),
    PERCENT("percent", "百分比");

    private final String code;
    private final String desc;

    SpacingMode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
