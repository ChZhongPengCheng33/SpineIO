package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.io.pipeline.context.Spine35Context;
import com.zhongpengcheng.spine.io.pipeline.executor.PipelineExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

@Slf4j
class BinarySkeletonReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "spineboy/spineboy.skel",
            "spineboy/spineboy-hover.skel",
            "spineboy/spineboy-mesh.skel",
            "stretchyman/stretchyman.skel",
            "tank/tank.skel",
    })
    void read(String skelPath) throws IOException {
        Spine35Context ctx = Spine35Context.of(skelPath);
        boolean success = PipelineExecutor.acceptSync(ctx);
        log.debug("执行结果: " + success);
    }
}