package com.zhongpengcheng.spine.io.v35.handler;

import com.zhongpengcheng.spine.io.v35.context.BinaryContext;
import com.zhongpengcheng.spine.io.v35.enums.PositionMode;
import com.zhongpengcheng.spine.io.v35.enums.RotateMode;
import com.zhongpengcheng.spine.io.v35.enums.SpacingMode;
import com.zhongpengcheng.spine.io.v35.pojo.Path;
import com.zhongpengcheng.spine.io.v35.Spine35DataInputStream;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 22:37:00
 */
@Slf4j
public class BinaryPathsReader extends AbstractBinaryReader {
    @Override
    public String getName() {
        return super.getName() + ":路径读取器";
    }

    @Override
    public boolean handle(BinaryContext ctx) throws IOException {
        Spine35DataInputStream input = ctx.getInput();
        for (int i = 0, pathCount = input.readInt(true); i < pathCount; i++) {
            Path path = Path.builder()
                    .name(input.readString())
                    .order(input.readInt(true))
                    .bones(super.readDependBones(ctx))
                    .target(ctx.getSlotName(input.readInt(true)))
                    .positionMode(PositionMode.values()[input.readInt(true)].getCode())
                    .spacingMode(SpacingMode.values()[input.readInt(true)].getCode())
                    .rotateMode(RotateMode.values()[input.readInt(true)].getCode())
                    .rotation(input.readFloat())
                    .position(input.readFloat())
                    .spacing(input.readFloat())
                    .rotateMix(input.readFloat())
                    .translateMix(input.readFloat())
                    .build();
            ctx.getPaths().add(path);
        }
        return true;
    }
}
