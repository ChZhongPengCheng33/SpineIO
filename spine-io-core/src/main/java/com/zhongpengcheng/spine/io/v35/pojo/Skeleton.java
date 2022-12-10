package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author ZhongPengCheng
 * @since 2021-03-01 23:18:00
 */
public class Skeleton {
    @Expose
    private Head head;
    @Expose
    private List<Bone> bones;
    @Expose
    private List<Slot> slots;
    @Expose
    private List<Ik> iks;
    @Expose
    private List<Transform> transforms;
    @Expose
    private List<Path> paths;
    @Expose
    private List<Skin> skins;
    @Expose
    private List<Event> events;
    @Expose
    private List<Animation> animations;

    public boolean nonessential() {
        if (head == null) return false;
        return Boolean.TRUE.equals(head.getNonessential());
    }

    /**
     * 根据boneIndex获取boneName
     * @param index 骨骼下标
     * @return 骨骼名称
     */
    public String getBoneName(int index) {
        return bones.get(index).getName();
    }

    /**
     * 根据slotIndex获取slotName
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

    public Head getHead() {
        return this.head;
    }

    public List<Bone> getBones() {
        return this.bones;
    }

    public List<Slot> getSlots() {
        return this.slots;
    }

    public List<Ik> getIks() {
        return this.iks;
    }

    public List<Transform> getTransforms() {
        return this.transforms;
    }

    public List<Path> getPaths() {
        return this.paths;
    }

    public List<Skin> getSkins() {
        return this.skins;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public List<Animation> getAnimations() {
        return this.animations;
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
