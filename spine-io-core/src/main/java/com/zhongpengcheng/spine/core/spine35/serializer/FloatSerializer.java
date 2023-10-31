package com.zhongpengcheng.spine.core.spine35.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * @author skyfire33
 * @since 2022-12-10 18:40:00
 */
public class FloatSerializer implements JsonSerializer<Float> {
    @Override
    public JsonElement serialize(Float src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(Float.parseFloat(String.format("%.2f", src)));
    }

    public static FloatSerializer singleton() {
        return InstanceHolder.instance;
    }

    public static class InstanceHolder {
        public static final FloatSerializer instance;

        static {
            instance = new FloatSerializer();
        }
    }
}
