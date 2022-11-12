package com.zhongpengcheng.spine.io.v35.handler;

import com.zhongpengcheng.spine.io.v35.context.BinaryContext;
import com.zhongpengcheng.spine.io.v35.pojo.Transform;
import com.zhongpengcheng.spine.io.v35.Spine35DataInputStream;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 22:37:00
 */
@Slf4j
public class BinaryTransformsReader extends AbstractBinaryReader {
    @Override
    public String getName() {
        return super.getName() + ":变换读取器";
    }

    @Override
    public boolean handle(BinaryContext ctx) throws IOException {
        Spine35DataInputStream input = ctx.getInput();
        for (int i = 0, n = input.readInt(true); i < n; i++) {
            Transform transform = Transform.builder()
                    .name(input.readString())
                    .order(input.readInt(true))
                    .bones(super.readDependBones(ctx))
                    .target(ctx.getBoneName(input.readInt(true)))
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

            ctx.getTransforms().add(transform);
        }
        return true;
    }
}
