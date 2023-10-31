package com.zhongpengcheng.spine.core.spine35v2.enums;

import lombok.Getter;

/**
 * 骨骼变换模式枚举
 *
 * @author zhongpengcheng
 * @since 2023-10-31 16:41:04
 */
@Getter
public enum TransformModeEnum {
    /**
     * 正常
     */
    NORMAL("normal"),
    /**
     * 仅翻译
     */
    ONLY_TRANSLATION("onlyTranslation"),
    /**
     * 没有旋转或反射
     */
    NO_ROTATION_OR_REFLECTION("noRotationOrReflection"),
    /**
     * 无缩放
     */
    NO_SCALE("noScale"),
    /**
     * 无缩放或反射
     */
    NO_SCALE_OR_REFLECTION("noScaleOrReflection");

    private final String code;

    TransformModeEnum(String code) {
        this.code = code;
    }
}
