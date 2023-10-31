package com.zhongpengcheng.spine.core.common.model;

import lombok.Getter;

/**
 * 颜色对象
 *
 * @author zhongpengcheng
 * @since 2023-10-31 16:55:22
 */
@Getter
public class Color {
    private final int intValue;

    public Color(int intValue) {
        this.intValue = intValue;
    }

    public static Color with(Integer intValue) {
        if (intValue == null) {
            return null;
        }
        return new Color(intValue);
    }
}
