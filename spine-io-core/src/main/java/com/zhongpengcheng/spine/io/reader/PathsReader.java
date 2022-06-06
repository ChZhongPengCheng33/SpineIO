package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.enums.PositionMode;
import com.zhongpengcheng.spine.enums.RotateMode;
import com.zhongpengcheng.spine.enums.SpacingMode;
import com.zhongpengcheng.spine.io.SpineDataInputStream;
import com.zhongpengcheng.spine.io.reader.AbstractReader;
import com.zhongpengcheng.spine.pojo.Path;
import com.zhongpengcheng.spine.pojo.Skeleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 路径读取器
 *
 * @author zhongpengcheng
 * @since 2022-01-26 14:32:51
 **/
public class PathsReader extends AbstractReader<List<Path>> {
    private final Skeleton skeleton;

    public PathsReader(SpineDataInputStream input, Skeleton skeleton) {
        super(input);
        this.skeleton = skeleton;
    }

    @Override
    public List<Path> read() throws IOException {
        List<Path> paths = new ArrayList<>();
        for (int i = 0, pathCount = input.readInt(true); i < pathCount; i++) {
            Path path = Path.builder()
                    .name(input.readString())
                    .order(input.readInt(true))
                    .bones(readDependBones(skeleton.getBones()))
                    .target(skeleton.getSlots().get(input.readInt(true)).getName())
                    .positionMode(PositionMode.values()[input.readInt(true)].getCode())
                    .spacingMode(SpacingMode.values()[input.readInt(true)].getCode())
                    .rotateMode(RotateMode.values()[input.readInt(true)].getCode())
                    .rotation(input.readFloat())
                    .position(input.readFloat())
                    .spacing(input.readFloat())
                    .rotateMix(input.readFloat())
                    .translateMix(input.readFloat())
                    .build();
            paths.add(path);
        }
        return paths;
    }
}
