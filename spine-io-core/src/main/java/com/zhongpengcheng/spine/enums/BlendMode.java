package com.zhongpengcheng.spine.enums;

/**
 *
 * @author ZhongPengCheng
 * @since 2021-03-02 1:37:00
 */
public enum BlendMode {
    NORMAL("normal", "基础渲染模式"),
    ADDITIVE("additive", "添加剂（机翻）"),
    MULTIPLY("multiply", "乘（机翻）"),
    SCREEN("screen", "屏幕（机翻）");

    private final String code;
    private final String desc;

    BlendMode(String code, String desc) {
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
