package com.zhongpengcheng.spine.core.spine35.enums;

import lombok.Getter;

/**
 * @author skyfire33
 * @since 2021-03-02 1:37:00
 */
@Getter
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

}
