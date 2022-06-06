package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.pojo.Skeleton;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;

@Slf4j
class BinarySkeletonReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "src/test/resources/spineboy/spineboy.skel",
            "src/test/resources/spineboy/spineboy-hover.skel",
            "src/test/resources/spineboy/spineboy-mesh.skel",
            "src/test/resources/stretchyman/stretchyman.skel",
            "src/test/resources/tank/tank.skel",
    })
    void read(String skelPath) throws IOException {
        File file = new File(skelPath);
        Skeleton skeleton = null;
        BinaryReader skeletonReader = new BinaryReader(file);
        skeleton = skeletonReader.read();
    }
}