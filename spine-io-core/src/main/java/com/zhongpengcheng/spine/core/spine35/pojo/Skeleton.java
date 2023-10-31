package com.zhongpengcheng.spine.core.spine35.pojo;

import lombok.Getter;

import java.util.List;

/**
 * @author skyfire33
 * @since 2021-03-01 23:18:00
 */
@Getter
public class Skeleton {
    
    private Head head;
    
    private List<Bone> bones;
    
    private List<Slot> slots;
    
    private List<Ik> iks;
    
    private List<Transform> transforms;
    
    private List<Path> paths;
    
    private List<Skin> skins;
    
    private List<Event> events;
    
    private List<Animation> animations;

    public boolean nonessential() {
        if (head == null) return false;
        return Boolean.TRUE.equals(head.getNonessential());
    }

    /**
     * 根据boneIndex获取boneName
     *
     * @param index 骨骼下标
     * @return 骨骼名称
     */
    public String getBoneName(int index) {
        return bones.get(index).getName();
    }

    /**
     * 根据slotIndex获取slotName
     *
     * @param index 插槽下标
     * @return 插槽名称
     */
    public String getSlotName(int index) {
        return slots.get(index).getName();
    }

    public String getIkName(int ikIndex) {
        return iks.get(ikIndex).getName();
    }

    public String getTransformName(int transformIndex) {
        return transforms.get(transformIndex).getName();
    }

    public Skeleton setHead(Head head) {
        this.head = head;
        return this;
    }

    public Skeleton setBones(List<Bone> bones) {
        this.bones = bones;
        return this;
    }

    public Skeleton setSlots(List<Slot> slots) {
        this.slots = slots;
        return this;
    }

    public Skeleton setIks(List<Ik> iks) {
        this.iks = iks;
        return this;
    }

    public Skeleton setTransforms(List<Transform> transforms) {
        this.transforms = transforms;
        return this;
    }

    public Skeleton setPaths(List<Path> paths) {
        this.paths = paths;
        return this;
    }

    public Skeleton setSkins(List<Skin> skins) {
        this.skins = skins;
        return this;
    }

    public Skeleton setEvents(List<Event> events) {
        this.events = events;
        return this;
    }

    public Skeleton setAnimations(List<Animation> animations) {
        this.animations = animations;
        return this;
    }
}
