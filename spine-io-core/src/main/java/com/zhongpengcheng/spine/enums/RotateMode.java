package com.zhongpengcheng.spine.enums;

import lombok.Getter;

/**
 * @author ZhongPengCheng
 * @since 2021-03-02 18:18:00
 */
@Getter
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
}
