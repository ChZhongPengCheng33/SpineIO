package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.io.SpineDataInputStream;
import com.zhongpengcheng.spine.io.reader.AbstractReader;
import com.zhongpengcheng.spine.pojo.Ik;
import com.zhongpengcheng.spine.pojo.Skeleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * IK约束读取器
 *
 * @author zhongpengcheng
 * @since 2022-01-26 13:24:39
 **/
public class IksReader extends AbstractReader<List<Ik>> {
    private final Skeleton skeleton;

    public IksReader(SpineDataInputStream input, Skeleton skeleton) {
        super(input);
        this.skeleton = skeleton;
    }

    @Override
    public List<Ik> read() throws IOException {
        List<Ik> iks = new ArrayList<>();
        for (int i = 0, n = input.readInt(true); i < n; i++) {
            Ik.IkBuilder ikBuilder = Ik.builder()
                    .name(input.readString())
                    .order(input.readInt(true))
                    .bones(super.readDependBones(skeleton.getBones()))
                    .target(skeleton.getBones().get(input.readInt(true)).getName())
                    .mix(input.readFloat())
                    .bendPositive((int) input.readByte());
            iks.add(ikBuilder.build());
        }

        return iks;
    }

}
