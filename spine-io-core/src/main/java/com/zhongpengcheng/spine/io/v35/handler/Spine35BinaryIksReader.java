package com.zhongpengcheng.spine.io.v35.handler;

import com.zhongpengcheng.spine.io.pipeline.context.Spine35Context;
import com.zhongpengcheng.spine.io.v35.pojo.Ik;
import com.zhongpengcheng.spine.io.v35.stream.Spine35DataInputStream;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 22:37:00
 */
@Slf4j
public class Spine35BinaryIksReader extends AbstractSpine35BinReader {
    @Override
    public String getName() {
        return super.getName() + ":ik约束读取器";
    }

    @Override
    public boolean handle(Spine35Context ctx) throws IOException {
        Spine35DataInputStream input = ctx.getInput();
        for (int i = 0, n = input.readInt(true); i < n; i++) {
            Ik.IkBuilder ikBuilder = Ik.builder()
                    .name(input.readString())
                    .order(input.readInt(true))
                    .bones(super.readDependBones(ctx))
                    .target(ctx.getBoneName(input.readInt(true)))
                    .mix(input.readFloat())
                    .bendPositive((int) input.readByte());
            ctx.getIks().add(ikBuilder.build());
        }
        return true;
    }
}
