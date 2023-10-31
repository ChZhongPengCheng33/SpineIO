package com.zhongpengcheng.spine.core.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * {@link Float}JSON序列化器
 *
 * @author zhongpengcheng
 * @since 2023-10-31 17:16:06
 */
public class FloatJsonSerializer extends JsonSerializer<Float> {
    @Override
    public void serialize(Float value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(Math.round( value * 100) / 100);
    }
}
