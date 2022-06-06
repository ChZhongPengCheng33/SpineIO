package com.zhongpengcheng.spine.io.pipeline.handler;

import com.zhongpengcheng.spine.io.pipeline.context.PipelineContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 21:16:00
 */
@Slf4j
public class CommonPreHandler implements ContextHandler<PipelineContext> {
    @Override
    public String getName() {
        return "通用前置处理器";
    }

    @Override
    public boolean handle(PipelineContext ctx) {
        ctx.setStartProcessTime(new Date());
        log.debug("[{}]-管道[{}]执行开始", getName(), ctx);
        return true;
    }
}
