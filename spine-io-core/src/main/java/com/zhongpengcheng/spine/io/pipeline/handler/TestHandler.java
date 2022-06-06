package com.zhongpengcheng.spine.io.pipeline.handler;

import com.zhongpengcheng.spine.io.pipeline.context.Spine35Context;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 21:30:00
 */
@Slf4j
public class TestHandler implements ContextHandler<Spine35Context> {
    @Override
    public String getName() {
        return "测试处理器";
    }

    @Override
    public boolean handle(Spine35Context ctx) {
        log.debug("[{}]-[{}]-进入测试处理器", getName(), ctx);
        return true;
    }
}
