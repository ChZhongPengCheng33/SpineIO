package com.zhongpengcheng.spine.core.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zhongpengcheng.spine.core.common.model.Color;
import com.zhongpengcheng.spine.util.DataTypeUtils;

import java.io.IOException;

/**
 * {@link Color}JSON序列化器
 *
 * @author zhongpengcheng
 * @since 2023-10-31 17:17:26
 */
public class ColorJsonSerializer extends JsonSerializer<Color> {
    @Override
    public void serialize(Color value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(DataTypeUtils.intToHexColor(value.getIntValue()));
    }
}
