package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.enums.TransformMode;
import com.zhongpengcheng.spine.io.SpineDataInputStream;
import com.zhongpengcheng.spine.io.reader.AbstractReader;
import com.zhongpengcheng.spine.pojo.Bone;
import com.zhongpengcheng.spine.pojo.Skeleton;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 骨骼节点读取器
 *
 * @author zhongpengcheng
 * @since 2022-01-25 15:47:05
 **/
@Slf4j
@Getter
@Setter
public class BonesReader extends AbstractReader<List<Bone>> {

    /**
     * skel文件头
     */
    private Skeleton skeleton;

    public BonesReader(SpineDataInputStream input, Skeleton skeleton) {
        super(input);
        this.skeleton = skeleton;
    }

    @Override
    public List<Bone> read() throws IOException {
        List<Bone> bones = new ArrayList<>(DEFAULT_BONE_SIZE);
        for (int i = 0, boneCount = input.readInt(true); i < boneCount; i++) {
            // 读名称
            String name = input.readString();
            // 拿到父骨骼
            Bone parent = i == 0 ? null : bones.get(input.readInt(true));
            // 读基础属性
            Bone.BoneBuilder builder = Bone.builder()
                    .name(name)
                    .id(i)
                    .parent(parent == null ? null : parent.getName())
                    .rotation(input.readFloat())
                    .x(input.readFloat())
                    .y(input.readFloat())
                    .scaleX(input.readFloat())
                    .scaleY(input.readFloat())
                    .shearX(input.readFloat())
                    .shearY(input.readFloat())
                    .length(input.readFloat());
            // 读变换模式
            TransformMode transformMode = TransformMode.values()[input.readInt(true)];
            if (transformMode != TransformMode.NORMAL) {
                builder.transformMode(transformMode.getCode());
            }
            // 读非必须属性
            if (skeleton.nonessential()) {
                builder.color(super.rgba8888ToHexColor(input.readInt()));
            }
            bones.add(builder.build());
        }
        return bones;
    }
}
