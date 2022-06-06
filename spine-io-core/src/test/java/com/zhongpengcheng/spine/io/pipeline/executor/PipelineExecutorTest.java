package com.zhongpengcheng.spine.io.pipeline.executor;

import com.zhongpengcheng.spine.io.pipeline.context.Spine35Context;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PipelineExecutorTest {
    @Test
    void testRun() {
        boolean b = PipelineExecutor.acceptSync(new Spine35Context());
        System.out.println(b);
    }
}