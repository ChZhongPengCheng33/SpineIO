package com.zhongpengcheng.spine.core.spine35v2.serializer;

import com.google.common.collect.Lists;
import com.zhongpengcheng.spine.core.common.model.Color;
import com.zhongpengcheng.spine.core.spine35v2.enums.TransformModeEnum;
import com.zhongpengcheng.spine.core.spine35v2.model.Bone;
import com.zhongpengcheng.spine.core.spine35v2.model.Head;
import com.zhongpengcheng.spine.core.spine35v2.model.Skeleton;
import com.zhongpengcheng.spine.io.SpineIODataInputStream;

import java.util.List;
import java.util.function.Supplier;

/**
 * 二进制骨骼文件反序列化器
 *
 * @author zhongpengcheng
 * @since 2023-10-31 15:52:07
 */
public class BinaryDeserializer implements AutoCloseable {
    private final SpineIODataInputStream in;
    private boolean nonessential = Boolean.FALSE;
    private Head head;
    private List<Bone> bones;

    public BinaryDeserializer(SpineIODataInputStream in) {
        this.in = in;
    }

    public static BinaryDeserializer with(SpineIODataInputStream in) {
        return new BinaryDeserializer(in);
    }

    public Skeleton read() {
        readHead();
        readBones();
        readSlots();
        readIks();
        readTransforms();
        readPaths();
        readSkins();
        readEvents();
        readAnimations();

        return Skeleton.builder()
                .head(head)
                .bones(bones)
                .build();
    }

    private void readHead() {
        this.head = Head.builder()
                .hash(in.readString(null))
                .version(in.readString(null))
                .width(in.readFloat())
                .height(in.readFloat())
                .nonessential(readNonessential(in::readBoolean))
                .fps(nonessential ? in.readFloat() : null)
                .images(nonessential ? in.readString(null) : null)
                .build();
    }

    private boolean readNonessential(Supplier<Boolean> supplier) {
        Boolean nonessential = supplier.get();
        this.nonessential = Boolean.TRUE.equals(nonessential);
        return this.nonessential;
    }

    private void readBones() {
        int boneCount = in.readInt(true);
        List<Bone> bones = Lists.newArrayListWithExpectedSize(boneCount);

        for (int i = 0; i < boneCount; i++) {
            Bone.BoneBuilder builder = Bone.builder()
                    .name(in.readString())
                    .order(i)
                    .parentIndex(i == 0 ? null : in.readInt(true))
                    .rotation(in.readFloat())
                    .x(in.readFloat())
                    .y(in.readFloat())
                    .scaleX(in.readFloat())
                    .scaleY(in.readFloat())
                    .shearX(in.readFloat())
                    .shearY(in.readFloat())
                    .length(in.readFloat())
                    .transformMode(TransformModeEnum.values()[in.readInt(true)])
                    .color(Color.with(nonessential ? in.readInt() : null));
            bones.add(builder.build());
        }

        this.bones = bones;
    }

    private void readSlots() {
    }

    private void readIks() {
    }

    private void readTransforms() {
    }

    private void readPaths() {
    }

    private void readSkins() {
    }

    private void readEvents() {
    }

    private void readAnimations() {
    }

    @Override
    public void close() {
        in.close();
    }
}
