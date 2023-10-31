package com.zhongpengcheng.spine.core.spine35.pojo.attachment;

/**
 * @author skyfire33
 * @since 2021-03-02 18:48:00
 */
public class PathAttachment implements IAttachment {
    private String slot;
    private String type;
    private String name;
    private Float[] lengths;
    private Boolean closed;
    private Boolean constantSpeed;
    private String color;
    private Integer vertexCount;
    private Integer[] bones;
    private Float[] vertices;
    private Integer worldVerticesLength;
    private int slotIndex;

    public PathAttachment(String slot, String type, String name, Float[] lengths, Boolean closed, Boolean constantSpeed, String color, Integer vertexCount, Integer[] bones, Float[] vertices, Integer worldVerticesLength, int slotIndex) {
        this.slot = slot;
        this.type = type;
        this.name = name;
        this.lengths = lengths;
        this.closed = closed;
        this.constantSpeed = constantSpeed;
        this.color = color;
        this.vertexCount = vertexCount;
        this.bones = bones;
        this.vertices = vertices;
        this.worldVerticesLength = worldVerticesLength;
        this.slotIndex = slotIndex;
    }

    public PathAttachment() {
    }

    @Override
    public boolean weighted() {
        return bones != null;
    }

    @Override
    public Float[] getVertices() {
        return vertices;
    }

    @Override
    public String getAttachmentName() {
        return this.name;
    }

    public String getSlot() {
        return this.slot;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public Float[] getLengths() {
        return this.lengths;
    }

    public Boolean getClosed() {
        return this.closed;
    }

    public Boolean getConstantSpeed() {
        return this.constantSpeed;
    }

    public String getColor() {
        return this.color;
    }

    public Integer getVertexCount() {
        return this.vertexCount;
    }

    public Integer[] getBones() {
        return this.bones;
    }

    public Integer getWorldVerticesLength() {
        return this.worldVerticesLength;
    }

    public int getSlotIndex() {
        return this.slotIndex;
    }

    public PathAttachment setSlot(String slot) {
        this.slot = slot;
        return this;
    }

    public PathAttachment setType(String type) {
        this.type = type;
        return this;
    }

    public PathAttachment setName(String name) {
        this.name = name;
        return this;
    }

    public PathAttachment setLengths(Float[] lengths) {
        this.lengths = lengths;
        return this;
    }

    public PathAttachment setClosed(Boolean closed) {
        this.closed = closed;
        return this;
    }

    public PathAttachment setConstantSpeed(Boolean constantSpeed) {
        this.constantSpeed = constantSpeed;
        return this;
    }

    public PathAttachment setColor(String color) {
        this.color = color;
        return this;
    }

    public PathAttachment setVertexCount(Integer vertexCount) {
        this.vertexCount = vertexCount;
        return this;
    }

    public PathAttachment setBones(Integer[] bones) {
        this.bones = bones;
        return this;
    }

    public PathAttachment setVertices(Float[] vertices) {
        this.vertices = vertices;
        return this;
    }

    public PathAttachment setWorldVerticesLength(Integer worldVerticesLength) {
        this.worldVerticesLength = worldVerticesLength;
        return this;
    }

    public PathAttachment setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
        return this;
    }
}
