package com.zhongpengcheng.spine.fastjson.serializer;

import java.util.HashMap;

/**
 * @author zhongpengcheng
 * @since 2022-02-18 16:11:39
 **/
public class TimelineBlockSerializerFactory {
    private static final HashMap<String, TimelineBlockSerializer> serializerCache;

    static {
        serializerCache = new HashMap<>();
    }

    /**
     * 获取时间线对应的序列化器
     */
    public static TimelineBlockSerializer serializerOf(String timelineType) {
        if (timelineType == null) throw new NullPointerException("timeline is null.");
        return serializerCache.get(timelineType);
    }
}
