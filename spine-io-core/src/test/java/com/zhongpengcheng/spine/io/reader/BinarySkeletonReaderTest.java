package com.zhongpengcheng.spine.io.reader;

import cn.hutool.core.io.IoUtil;
import com.zhongpengcheng.spine.io.pojo.Skeleton;
import com.zhongpengcheng.spine.io.stream.Spine35DataInputStream;
import com.zhongpengcheng.spine.util.GsonUtils;
import com.zhongpengcheng.spine.util.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
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
        Skeleton skeleton;
        try (BinarySkeletonReader reader = BinarySkeletonReaderBuilder
                .newInstance()
                .input(IOUtils.inputStreamOf(path))
                .build()) {

            skeleton = reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(GsonUtils.newInstanceWithPrettyPrinting().toJson(skeleton));
    }
}