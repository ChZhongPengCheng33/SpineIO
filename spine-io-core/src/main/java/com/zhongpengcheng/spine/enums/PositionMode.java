package com.zhongpengcheng.spine.enums;

import lombok.Getter;

/**
 * @author ZhongPengCheng
 * @since 2021-03-02 18:17:00
 */
@Getter
public enum PositionMode {
    FIXED("fixed", "固定值"),
    PERCENT("percent", "百分比");

    private final String code;
    private final String desc;

    PositionMode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
