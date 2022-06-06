package com.zhongpengcheng.spine.fastjson;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

class FastjsonSerializerTest {

    @ParameterizedTest
    @ValueSource(strings = {
//            "src/test/resources/spineboy/spineboy.skel",
//            "src/test/resources/spineboy/spineboy-hover.skel",
//            "src/test/resources/spineboy/spineboy-mesh.skel",
//            "src/test/resources/stretchyman/stretchyman.skel",
            "src/test/resources/tank/tank.skel",
    })
    void testSerialize(String skelPath) throws IOException {
    }
}