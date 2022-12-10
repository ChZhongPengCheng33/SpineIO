package com.zhongpengcheng.spine.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;
import com.zhongpengcheng.spine.io.v35.pojo.Head;
import com.zhongpengcheng.spine.io.v35.pojo.Skeleton;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GsonUtilsTest {
    @Test
    void testFormat() {
        String json = "{\"Param\":{\"stringtype\":\"This is String\",\"timestamp\":1597817205000,\"arr\":[12334,2323,334344],\"double\":7.0},\"Type\":\"/api/test/getName\"}";

        Gson gson = new GsonBuilder()
                .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .create();

        Map<String, Object> objectMap = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());

        System.out.println(objectMap);
    }
}