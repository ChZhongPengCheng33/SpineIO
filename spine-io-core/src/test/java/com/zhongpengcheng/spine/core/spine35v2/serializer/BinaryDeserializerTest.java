package com.zhongpengcheng.spine.core.spine35v2.serializer;

import com.zhongpengcheng.spine.SpineIO;
import com.zhongpengcheng.spine.core.spine35v2.model.Skeleton;
import com.zhongpengcheng.spine.util.IOUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BinaryDeserializerTest {
    @SneakyThrows
    @ParameterizedTest
    @ValueSource(strings = {
            "spineboy/spineboy.skel",
            "spineboy/spineboy-hover.skel",
            "spineboy/spineboy-mesh.skel",
            "stretchyman/stretchyman.skel",
            "tank/tank.skel"})
    void testRead(String path) {
        try (BinaryDeserializer deserializer = BinaryDeserializer.with(IOUtils.inputStreamOf(path))) {
            Skeleton skeleton = deserializer.read();
            System.out.println(SpineIO.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(skeleton));
        }
    }
}