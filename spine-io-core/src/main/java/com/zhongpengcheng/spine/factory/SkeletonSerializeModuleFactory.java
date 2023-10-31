package com.zhongpengcheng.spine.factory;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zhongpengcheng.spine.core.common.serializer.ColorJsonSerializer;
import com.zhongpengcheng.spine.core.common.serializer.FloatJsonSerializer;
import com.zhongpengcheng.spine.core.common.serializer.TransformModeJsonSerializer;
import com.zhongpengcheng.spine.core.spine35v2.enums.TransformModeEnum;
import com.zhongpengcheng.spine.core.common.model.Color;
import com.zhongpengcheng.spine.core.spine35v2.model.Skeleton;
import com.zhongpengcheng.spine.core.spine35v2.serializer.SkeletonSerializer;

/**
 * 骨骼Json序列化模块工厂
 *
 * @author zhongpengcheng
 * @since 2023-10-31 17:09:38
 */
public class SkeletonSerializeModuleFactory {
    public SimpleModule createModule(String name, Version version) {
        return new SimpleModule(name, version)
                .addSerializer(Float.class, new FloatJsonSerializer())
                .addSerializer(Color.class, new ColorJsonSerializer())
                .addSerializer(TransformModeEnum.class, new TransformModeJsonSerializer())
                .addSerializer(Skeleton.class, new SkeletonSerializer());
    }
}
