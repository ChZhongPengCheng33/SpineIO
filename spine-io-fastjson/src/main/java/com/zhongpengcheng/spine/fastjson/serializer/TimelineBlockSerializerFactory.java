package com.zhongpengcheng.spine.fastjson.serializer;

import com.zhongpengcheng.spine.fastjson.serializer.impl.BoneBlockSerializer;
import com.zhongpengcheng.spine.fastjson.serializer.impl.IkBlockSerializer;
import com.zhongpengcheng.spine.fastjson.serializer.impl.PathBlockSerializer;
import com.zhongpengcheng.spine.fastjson.serializer.impl.SlotBlockSerializer;
import com.zhongpengcheng.spine.io.reader.AnimationsReader;

import java.util.HashMap;

/**
 * @author zhongpengcheng
 * @since 2022-02-18 16:11:39
 **/
public class TimelineBlockSerializerFactory {
    private static final HashMap<String, TimelineBlockSerializer> serializerCache;

    static {
        serializerCache = new HashMap<>();
        serializerCache.put(AnimationsReader.TimelinesReader.SLOT_KEY, new SlotBlockSerializer());
        serializerCache.put(AnimationsReader.TimelinesReader.BONE_KEY, new BoneBlockSerializer());
        serializerCache.put(AnimationsReader.TimelinesReader.IK_KEY, new IkBlockSerializer());
        serializerCache.put(AnimationsReader.TimelinesReader.PATH_KEY, new PathBlockSerializer());
    }

    /**
     * 获取时间线对应的序列化器
     */
    public static TimelineBlockSerializer serializerOf(String timelineType) {
        if (timelineType == null) throw new NullPointerException("timeline is null.");
        return serializerCache.get(timelineType);
    }
}
