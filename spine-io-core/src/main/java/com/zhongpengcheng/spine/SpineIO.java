package com.zhongpengcheng.spine;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * SpineIO门面
 *
 * @author skyfire33
 * @since 2022-12-10 18:40:00
 */
@Slf4j
public class SpineIO {
    @Getter
    @Setter
    private static ObjectMapper objectMapper;
    public static final Version VERSION = new Version(1, 0, 0, null, "com.zhongpengcheng.spine", "spine-io");

    static {
        SimpleModule module = new SimpleModule("SpineIO Deserializer", VERSION);
        module.addSerializer(Float.class, new JsonSerializer<Float>() {
            @Override
            public void serialize(Float value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeNumber(Math.round(value * 100) / 100);
            }
        });
        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(module);
    }
}
