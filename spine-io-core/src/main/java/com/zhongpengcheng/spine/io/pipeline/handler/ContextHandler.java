package com.zhongpengcheng.spine.io.pipeline.handler;

import com.zhongpengcheng.spine.io.pipeline.context.PipelineContext;

import java.io.IOException;

/**
 * 上下文处理器
 * @author ZhongPengCheng
 * @since 2022-06-06 21:12:00
 */
public interface ContextHandler<T extends PipelineContext> {
    /**
     * 获取处理器名称
     * @return 处理器名称
     */
    String getName();
    /**
     * 对上下文进行处理
     * @param ctx 上下文实例
     * @return 处理结果
     */
    boolean handle(T ctx) throws IOException;
}
