package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.SpineIO;
import com.zhongpengcheng.spine.core.spine35.pojo.Skeleton;
import com.zhongpengcheng.spine.core.spine35.reader.BinarySkeletonReader;
import com.zhongpengcheng.spine.core.spine35.reader.BinarySkeletonReaderBuilder;
import com.zhongpengcheng.spine.util.IOUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

class BinarySkeletonReaderTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "spineboy/spineboy.skel",
            "spineboy/spineboy-hover.skel",
            "spineboy/spineboy-mesh.skel",
            "stretchyman/stretchyman.skel",
            "tank/tank.skel"})
    void testRead(String path) {
        try (BinarySkeletonReader reader = BinarySkeletonReaderBuilder
                .newInstance()
                .input(IOUtils.inputStreamOf(path))
                .build()) {
            Skeleton skeleton = reader.read();
            System.out.println(SpineIO.getObjectMapper().writeValueAsString(skeleton));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}