package com.zhongpengcheng.spine.io.enums;

/**
 * @author skyfire33
 * @since 2021-03-02 18:18:00
 */
public enum RotateMode {
    TANGENT("tangent", "切线"),
    CHAIN("chain", "链"),
    CHAIN_SCALE("chainScale", "缩放链");

    private final String code;
    private final String desc;

    RotateMode(String code, String desc) {
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
