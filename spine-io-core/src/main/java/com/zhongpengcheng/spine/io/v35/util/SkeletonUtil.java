package com.zhongpengcheng.spine.io.v35.util;

import com.zhongpengcheng.spine.io.v35.pojo.*;
import lombok.extern.slf4j.Slf4j;

/**
 * skeleton工具类
 *
 * @author zhongpengcheng
 * @since 2022-02-16 10:26:16
 **/
@Slf4j
public class SkeletonUtil {

    public static final Slot EMPTY_SLOT = Slot.builder().build();
    public static final Bone EMPTY_BONE = Bone.builder().build();
    public static final Ik EMPTY_IK = Ik.builder().build();
    public static final Transform EMPTY_TRANSFORM = Transform.builder().build();
    public static final Path EMPTY_PATH = Path.builder().build();

    /**
     * 获取{@link Skeleton#getSlots()}中对应下标的插槽
     * @param skeleton 目标{@link Skeleton}
     * @param slotIndex {@link Slot}下标
     * @return 当{@link Skeleton}为空或{@link Skeleton#getSlots()}为空{@link Skeleton#getSlots()}的size<=slotIndex时会返回null
     */
    public static Slot slotOf(Skeleton skeleton, int slotIndex) {
        if (skeleton == null) return EMPTY_SLOT;
        if (skeleton.getSlots() == null) return EMPTY_SLOT;
        if (skeleton.getSlots().size() <= slotIndex) return EMPTY_SLOT;

        return skeleton.getSlots().get(slotIndex);
    }

    /**
     * 获取{@link Skeleton#getBones()}中对应下标的骨骼
     * @param skeleton 目标{@link Skeleton}
     * @param boneIndex {@link Bone}下标
     * @return 当{@link Skeleton}为空或{@link Skeleton#getBones()}为空{@link Skeleton#getBones()}的size<=boneIndex时会返回null
     */
    public static Bone boneOf(Skeleton skeleton, int boneIndex) {
        if (skeleton == null) return EMPTY_BONE;
        if (skeleton.getBones() == null) return EMPTY_BONE;
        if (skeleton.getBones().size() <= boneIndex) return EMPTY_BONE;

        return skeleton.getBones().get(boneIndex);
    }

    /**
     * 获取{@link Skeleton#getIks()}中对应下标的ik约束
     * @param skeleton 目标{@link Skeleton}
     * @param ikIndex {@link Ik}下标
     * @return 当{@link Skeleton}为空或{@link Skeleton#getIks()}为空{@link Skeleton#getIks()}的size<=ikIndex时会返回null
     */
    public static Ik ikOf(Skeleton skeleton, int ikIndex) {
        if (skeleton == null) return EMPTY_IK;
        if (skeleton.getIks() == null) return EMPTY_IK;
        if (skeleton.getIks().size() <= ikIndex) return EMPTY_IK;

        return skeleton.getIks().get(ikIndex);
    }


    /**
     * 获取{@link Skeleton#getTransforms()}中对应下标的transform
     * @param skeleton 目标{@link Skeleton}
     * @param transformIndex {@link Transform}下标
     * @return 当{@link Skeleton}为空或{@link Skeleton#getTransforms()}为空{@link Skeleton#getTransforms()}的size<=transformIndex时会返回null
     */
    public static Transform transformOf(Skeleton skeleton, int transformIndex) {
        if (skeleton == null) return EMPTY_TRANSFORM;
        if (skeleton.getTransforms() == null) return EMPTY_TRANSFORM;
        if (skeleton.getTransforms().size() <= transformIndex) return EMPTY_TRANSFORM;

        return skeleton.getTransforms().get(transformIndex);
    }

    /**
     * 获取{@link Skeleton#getPaths()}中对应下标的path
     * @param skeleton 目标{@link Skeleton}
     * @param pathIndex {@link Path}下标
     * @return 当{@link Skeleton}为空或{@link Skeleton#getPaths()}为空{@link Skeleton#getPaths()}的size<=pathIndex时会返回null
     */
    public static Path pathOf(Skeleton skeleton, int pathIndex) {
        if (skeleton == null) return EMPTY_PATH;
        if (skeleton.getPaths() == null) return EMPTY_PATH;
        if (skeleton.getPaths().size() <= pathIndex) return EMPTY_PATH;

        return skeleton.getPaths().get(pathIndex);
    }
}
