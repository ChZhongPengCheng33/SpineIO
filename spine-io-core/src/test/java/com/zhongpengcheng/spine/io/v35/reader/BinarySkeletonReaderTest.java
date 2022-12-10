package com.zhongpengcheng.spine.io.v35.reader;

import com.zhongpengcheng.spine.io.v35.pojo.Skeleton;
import com.zhongpengcheng.spine.util.GsonUtils;
import com.zhongpengcheng.spine.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BinarySkeletonReaderTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "spineboy/spineboy.skel",
            "spineboy/spineboy-hover.skel",
            "spineboy/spineboy-mesh.skel",
            "stretchyman/stretchyman.skel",
            "tank/tank.skel",
    })
    void testRead(String path) {
        BinarySkeletonReader reader = BinarySkeletonReaderBuilder.newInstance()
                .input(IOUtils.inputStreamOf(path))
                .build();

        Skeleton skeleton = reader.read();

        System.out.println(GsonUtils.newInstanceWithPrettyPrinting().toJson(skeleton));
    }
}