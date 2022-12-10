package com.zhongpengcheng.spine.io.enums;

/**
 * 确定骨骼如何从父骨骼继承世界变换
 *
 * @author skyfire33
 * @since 2021-03-02 1:56:00
 */
public enum TransformMode {
    /**
     * 正常
     */
    NORMAL("normal", "正常变换"),
    /**
     * 仅翻译
     */
    ONLY_TRANSLATION("onlyTranslation", "仅翻译"),
    /**
     * 没有旋转或反射
     */
    NO_ROTATION_OR_REFLECTION("noRotationOrReflection", "没有旋转或反射"),
    /**
     * 无缩放
     */
    NO_SCALE("noScale", "无缩放"),
    /**
     * 无缩放或反射
     */
    NO_SCALE_OR_REFLECTION("noScaleOrReflection", "无缩放或反射");

    /**
     * 转换模式在skel文件中的key
     */
    private final String code;
    /**
     * 转换模式描述
     */
    private final String desc;

    TransformMode(String code, String desc) {
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
