package com.zhongpengcheng.spine.io.pipeline.route;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import com.zhongpengcheng.spine.io.pipeline.context.PipelineContext;
import com.zhongpengcheng.spine.io.pipeline.context.Spine35Context;
import com.zhongpengcheng.spine.io.pipeline.handler.ContextHandler;
import com.zhongpengcheng.spine.io.v35.handler.*;
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
        ROUTE_MAP.put(Spine35Context.class, CollectionUtil.toList(
                Spine35BinaryHeadReader.class,
                Spine35BinaryBonesReader.class,
                Spine35BinarySlotsReader.class,
                Spine35BinaryIksReader.class,
                Spine35BinaryTransformsReader.class,
                Spine35BinaryPathsReader.class,
                Spine35BinarySkinsReader.class,
                Spine35BinaryEventsReader.class,
                Spine35BinaryAnimationsReader.class
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
