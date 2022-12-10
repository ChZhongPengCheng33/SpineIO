package com.zhongpengcheng.spine.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.zhongpengcheng.spine.io.v35.adapter.SkeletonAdapter;
import com.zhongpengcheng.spine.io.v35.pojo.Skeleton;
import com.zhongpengcheng.spine.io.v35.serializer.DoubleSerializer;
import com.zhongpengcheng.spine.io.v35.serializer.FloatSerializer;

public class GsonUtils {
    public static Gson newInstance() {
        return baseGsonBuilder()
                .create();
    }

    public static Gson newInstanceWithPrettyPrinting() {
        return baseGsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    private static GsonBuilder baseGsonBuilder() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Skeleton.class, SkeletonAdapter.singleton())
                .registerTypeAdapter(Double.class, DoubleSerializer.singleton())
                .registerTypeAdapter(Float.class, FloatSerializer.singleton());
    }

    public static Gson singleton() {
        return GsonInstanceHolder.instance;
    }

    public static class GsonInstanceHolder {
        public static final Gson instance;

        static {
            instance = GsonUtils.newInstance();
        }
    }
}
