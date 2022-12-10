package com.zhongpengcheng.spine.io.route;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import com.zhongpengcheng.spine.io.context.PipelineContext;
import com.zhongpengcheng.spine.io.handler.ContextHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 21:27:00
 */
@Slf4j
public class PipelineRoute {
    /**
     * 路由表
     */
    private static final Map<Class<? extends PipelineContext>,
            List<Class<? extends ContextHandler<? extends PipelineContext>>>> ROUTE_MAP = new HashMap<>();
    /**
     * 路由实例缓存
     */
    private static Map<Class<? extends PipelineContext>, List<? extends ContextHandler<? extends PipelineContext>>> cacheRoute;

    static {
        // Spine v3.5.**
        ROUTE_MAP.put(com.zhongpengcheng.spine.io.v35.context.BinaryContext.class, CollectionUtil.toList(
                com.zhongpengcheng.spine.io.v35.handler.BinaryHeadReader.class,
                com.zhongpengcheng.spine.io.v35.handler.BinaryBonesReader.class,
                com.zhongpengcheng.spine.io.v35.handler.BinarySlotsReader.class,
                com.zhongpengcheng.spine.io.v35.handler.BinaryIksReader.class,
                com.zhongpengcheng.spine.io.v35.handler.BinaryTransformsReader.class,
                com.zhongpengcheng.spine.io.v35.handler.BinaryPathsReader.class,
                com.zhongpengcheng.spine.io.v35.handler.BinarySkinsReader.class,
                com.zhongpengcheng.spine.io.v35.handler.BinaryEventsReader.class,
                com.zhongpengcheng.spine.io.v35.handler.BinaryAnimationsReader.class
        ));
    }

    /**
     * 获取路由实例
     */
    public static Map<Class<? extends PipelineContext>, List<? extends ContextHandler<? extends PipelineContext>>> getRoute() {
        if (cacheRoute == null) {
            log.debug("进行处理器路由初始化");
            cacheRoute = ROUTE_MAP.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, PipelineRoute::toPipeline));
        }
        return cacheRoute;
    }

    /**
     * 将管道中的处理器实例化并返回
     */
    private static List<ContextHandler<? extends PipelineContext>> toPipeline(Map.Entry<Class<? extends PipelineContext>,
            List<Class<? extends ContextHandler<? extends PipelineContext>>>> entry) {
        return entry.getValue()
                .stream()
                .map(ReflectUtil::newInstanceIfPossible)
                .collect(Collectors.toList());
    }
}
