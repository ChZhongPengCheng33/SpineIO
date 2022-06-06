package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.enums.BlendMode;
import com.zhongpengcheng.spine.io.SpineDataInputStream;
import com.zhongpengcheng.spine.io.reader.AbstractReader;
import com.zhongpengcheng.spine.pojo.Skeleton;
import com.zhongpengcheng.spine.pojo.Slot;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 插槽读取器
 *
 * @author zhongpengcheng
 * @since 2022-01-25 16:24:29
 **/
@Slf4j
public class SlotsReader extends AbstractReader<List<Slot>> {
    /**
     * skel文件头
     */
    private final Skeleton skeleton;

    public SlotsReader(SpineDataInputStream input, Skeleton skeleton) {
        super(input);
        this.skeleton = skeleton;
    }

    @Override
    public List<Slot> read() throws IOException {
        List<Slot> slots = new ArrayList<>();
        for (int i = 0, n = input.readInt(true); i < n; i++) {
            Slot.SlotBuilder builder = Slot.builder();

            builder.name(input.readString())
                    .bone(skeleton.getBones().get(input.readInt(true)).getName())
                    .id(i);
            /*
            注意: 3.5.51.2版本这里多了一个int读取
            int color = input.readInt();
             */
            int darkColor = input.readInt();
            if (darkColor != NO_SLOT_COLOR) {
                builder.color(rgba8888ToHexColor(darkColor));
            }
            builder.attachment(input.readString());
            BlendMode blendMode = BlendMode.values()[input.readInt(true)];
            if (blendMode != BlendMode.NORMAL) {
                builder.blend(blendMode.getCode());
            }
            slots.add(builder.build());
        }

        return slots;
    }
}
