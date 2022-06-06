package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.io.SpineDataInputStream;
import com.zhongpengcheng.spine.io.reader.AbstractReader;
import com.zhongpengcheng.spine.pojo.Skeleton;
import com.zhongpengcheng.spine.pojo.Transform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 变换读取器
 *
 * @author zhongpengcheng
 * @since 2022-01-26 14:15:23
 **/
public class TransformsReader extends AbstractReader<List<Transform>> {
    private final Skeleton skeleton;

    public TransformsReader(SpineDataInputStream input, Skeleton skeleton) {
        super(input);
        this.skeleton = skeleton;
    }

    @Override
    public List<Transform> read() throws IOException {
        List<Transform> transforms= new ArrayList<>();
        for (int i = 0, n = input.readInt(true); i < n; i++) {

            Transform transform = Transform.builder()
                    .name(input.readString())
                    .order(input.readInt(true))
                    .bones(super.readDependBones(skeleton.getBones()))
                    .target(skeleton.getBones().get(input.readInt(true)).getName())
                    .rotation(input.readFloat())
                    .x(input.readFloat())
                    .y(input.readFloat())
                    .scaleX(input.readFloat())
                    .scaleY(input.readFloat())
                    .shearY(input.readFloat())
                    .rotateMix(input.readFloat())
                    .translateMix(input.readFloat())
                    .scaleMix(input.readFloat())
                    .shearMix(input.readFloat())
                    .build();

            transforms.add(transform);
        }
        return transforms;
    }
}
