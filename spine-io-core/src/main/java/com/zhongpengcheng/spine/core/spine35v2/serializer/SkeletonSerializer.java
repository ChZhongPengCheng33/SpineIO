package com.zhongpengcheng.spine.core.spine35v2.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zhongpengcheng.spine.core.spine35v2.model.Skeleton;

import java.io.IOException;

/**
 * {@link Skeleton}JSON序列化器
 *
 * @author zhongpengcheng
 * @since 2023-10-31 17:29:05
 */
public class SkeletonSerializer extends JsonSerializer<Skeleton> {
    @Override
    public void serialize(Skeleton value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("skeleton", value.getHead());
        gen.writeEndObject();
    }
}
