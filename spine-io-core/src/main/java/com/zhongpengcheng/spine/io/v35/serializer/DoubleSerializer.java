package com.zhongpengcheng.spine.io.v35.serializer;

import com.google.gson.*;

import java.lang.reflect.Type;

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
