package com.zhongpengcheng.spine.io.reader;

import com.alibaba.fastjson.JSON;
import com.zhongpengcheng.spine.io.v35.context.BinaryContext;
import com.zhongpengcheng.spine.io.executor.PipelineExecutor;
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
        BinaryContext ctx = BinaryContext.of(skelPath);
        boolean success = PipelineExecutor.acceptSync(ctx);
        log.debug("执行结果: " + success);
        log.debug(JSON.toJSONString(ctx));
    }
}