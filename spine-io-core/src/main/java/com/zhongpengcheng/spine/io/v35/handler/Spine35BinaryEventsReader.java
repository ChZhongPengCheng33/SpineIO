package com.zhongpengcheng.spine.io.v35.handler;

import com.zhongpengcheng.spine.io.pipeline.context.Spine35Context;
import com.zhongpengcheng.spine.io.v35.pojo.Event;
import com.zhongpengcheng.spine.io.v35.stream.Spine35DataInputStream;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 22:38:00
 */
@Slf4j
public class Spine35BinaryEventsReader extends AbstractSpine35BinReader {
    @Override
    public String getName() {
        return super.getName() + ":事件读取器";
    }

    @Override
    public boolean handle(Spine35Context ctx) throws IOException {
        Spine35DataInputStream input = ctx.getInput();
        for (int i = 0, n = input.readInt(true); i < n; i++) {
            Event event = Event.builder()
                    .name(input.readString())
                    .aInt(input.readInt(false))
                    .aFloat(input.readFloat())
                    .aString(input.readString())
                    .build();

            ctx.getEvents().add(event);
        }
        return true;
    }
}
