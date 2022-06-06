package com.zhongpengcheng.spine.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhongpengcheng.spine.fastjson.filter.FloatFilter;
import com.zhongpengcheng.spine.io.reader.BinaryReader;
import com.zhongpengcheng.spine.pojo.Skeleton;
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
        BinaryReader binaryReader = new BinaryReader(skelPath);
        Skeleton skeleton = binaryReader.read();
        JSONObject serializeResult = new FastjsonSerializer().serialize(skeleton);
        System.out.println(JSONObject.toJSONString(serializeResult, new FloatFilter(), SerializerFeature.PrettyFormat));
    }
}