package com.zhongpengcheng.spine.io.handler;

import com.zhongpengcheng.spine.io.context.PipelineContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 21:16:00
 */
@Slf4j
public class CommonPostHandler implements ContextHandler<PipelineContext> {
    @Override
    public String getName() {
        return "通用后置处理请";
    }

    @Override
    public boolean handle(PipelineContext ctx) {
        ctx.setEndProcessTime(new Date());
        log.debug("[{}]-管道[{}]-执行结束, 耗时[{}]ms", getName(), ctx, ctx.getCostTimeMillis());
        return true;
    }
}
