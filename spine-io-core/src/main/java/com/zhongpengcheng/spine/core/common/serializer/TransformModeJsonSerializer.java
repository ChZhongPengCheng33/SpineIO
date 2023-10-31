package com.zhongpengcheng.spine.core.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zhongpengcheng.spine.core.spine35v2.enums.TransformModeEnum;

import java.io.IOException;

/**
 * {@link TransformModeEnum}JSON序列化器
 *
 * @author zhongpengcheng
 * @since 2023-10-31 17:20:38
 */
public class TransformModeJsonSerializer extends JsonSerializer<TransformModeEnum> {
    @Override
    public void serialize(TransformModeEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.getCode());
    }
}
