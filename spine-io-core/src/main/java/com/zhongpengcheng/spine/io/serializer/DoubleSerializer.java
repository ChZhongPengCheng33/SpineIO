package com.zhongpengcheng.spine.io.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * @author skyfire33
 * @since 2022-12-10 18:40:00
 */
public class DoubleSerializer implements JsonSerializer<Double> {
    @Override
    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(Double.parseDouble(String.format("%.2f", src)));
    }

    public static DoubleSerializer singleton() {
        return InstanceHolder.instance;
    }

    public static class InstanceHolder {
        public static final DoubleSerializer instance;

        static {
            instance = new DoubleSerializer();
        }
    }
}
