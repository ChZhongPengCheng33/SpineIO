package com.zhongpengcheng.spine.io.v35.handler;

import com.zhongpengcheng.spine.io.v35.context.BinaryContext;
import com.zhongpengcheng.spine.io.v35.enums.BlendMode;
import com.zhongpengcheng.spine.io.v35.pojo.Slot;
import com.zhongpengcheng.spine.io.v35.Spine35DataInputStream;
import com.zhongpengcheng.spine.util.ColorUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * spine35版本二进制骨骼插槽读取器
 *
 * @author ZhongPengCheng
 * @since 2022-06-06 22:37:00
 */
@Slf4j
public class BinarySlotsReader extends AbstractBinaryReader {
    @Override
    public String getName() {
        return super.getName() + ":插槽读取器";
    }

    @Override
    public boolean handle(BinaryContext ctx) throws IOException {
        Spine35DataInputStream input = ctx.getInput();
        for (int i = 0, n = input.readInt(true); i < n; i++) {
            Slot.SlotBuilder builder = Slot.builder();

            builder.name(input.readString())
                    .bone(ctx.getBoneName(input.readInt(true)))
                    .id(i);
            /*
            注意: 3.5.51.2版本这里多了一个int读取
            int color = input.readInt();
             */
            int darkColor = input.readInt();
            if (darkColor != NO_SLOT_COLOR) {
                builder.color(ColorUtils.rgba8888ToHexColor(darkColor));
            }
            builder.attachment(input.readString());
            BlendMode blendMode = BlendMode.values()[input.readInt(true)];
            if (blendMode != BlendMode.NORMAL) {
                builder.blend(blendMode.getCode());
            }
            ctx.getSlots().add(builder.build());
        }
        return true;
    }
}
