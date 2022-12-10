package com.zhongpengcheng.spine.io.pojo.attachment;

import com.zhongpengcheng.spine.io.enums.AttachmentType;

/**
 * @author skyfire33
 * @since 2021-03-02 18:48:00
 */
public class BoundingBoxAttachment implements IAttachment {
    private String slot;
    private String name;
    private String type = AttachmentType.BOUNDING_BOX.getCode();
    /**
     * if null, name
     */
    private String path;
    /**
     * 非必须参数
     */
    private String color;
    private Integer vertexCount;
    private Float[] uvs;
    private Short[] triangles;
    /**
     * 顶点向量
     */
    private Integer[] bones;
    private Float[] vertices;
    private Integer worldVerticesLength;
    private Integer hullLength;
    /*如果nonessential为true则读取*/
    private Short[] edges;
    private Float width;
    private Float height;
    private int slotIndex;

    public BoundingBoxAttachment(String slot, String name, String type, String path, String color, Integer vertexCount, Float[] uvs, Short[] triangles, Integer[] bones, Float[] vertices, Integer worldVerticesLength, Integer hullLength, Short[] edges, Float width, Float height, int slotIndex) {
        this.slot = slot;
        this.name = name;
        this.type = type;
        this.path = path;
        this.color = color;
        this.vertexCount = vertexCount;
        this.uvs = uvs;
        this.triangles = triangles;
        this.bones = bones;
        this.vertices = vertices;
        this.worldVerticesLength = worldVerticesLength;
        this.hullLength = hullLength;
        this.edges = edges;
        this.width = width;
        this.height = height;
        this.slotIndex = slotIndex;
    }

    public BoundingBoxAttachment() {
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

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getPath() {
        return this.path;
    }

    public String getColor() {
        return this.color;
    }

    public Integer getVertexCount() {
        return this.vertexCount;
    }

    public Float[] getUvs() {
        return this.uvs;
    }

    public Short[] getTriangles() {
        return this.triangles;
    }

    public Integer[] getBones() {
        return this.bones;
    }

    public Integer getWorldVerticesLength() {
        return this.worldVerticesLength;
    }

    public Integer getHullLength() {
        return this.hullLength;
    }

    public Short[] getEdges() {
        return this.edges;
    }

    public Float getWidth() {
        return this.width;
    }

    public Float getHeight() {
        return this.height;
    }

    public int getSlotIndex() {
        return this.slotIndex;
    }

    public BoundingBoxAttachment setSlot(String slot) {
        this.slot = slot;
        return this;
    }

    public BoundingBoxAttachment setName(String name) {
        this.name = name;
        return this;
    }

    public BoundingBoxAttachment setType(String type) {
        this.type = type;
        return this;
    }

    public BoundingBoxAttachment setPath(String path) {
        this.path = path;
        return this;
    }

    public BoundingBoxAttachment setColor(String color) {
        this.color = color;
        return this;
    }

    public BoundingBoxAttachment setVertexCount(Integer vertexCount) {
        this.vertexCount = vertexCount;
        return this;
    }

    public BoundingBoxAttachment setUvs(Float[] uvs) {
        this.uvs = uvs;
        return this;
    }

    public BoundingBoxAttachment setTriangles(Short[] triangles) {
        this.triangles = triangles;
        return this;
    }

    public BoundingBoxAttachment setBones(Integer[] bones) {
        this.bones = bones;
        return this;
    }

    public BoundingBoxAttachment setVertices(Float[] vertices) {
        this.vertices = vertices;
        return this;
    }

    public BoundingBoxAttachment setWorldVerticesLength(Integer worldVerticesLength) {
        this.worldVerticesLength = worldVerticesLength;
        return this;
    }

    public BoundingBoxAttachment setHullLength(Integer hullLength) {
        this.hullLength = hullLength;
        return this;
    }

    public BoundingBoxAttachment setEdges(Short[] edges) {
        this.edges = edges;
        return this;
    }

    public BoundingBoxAttachment setWidth(Float width) {
        this.width = width;
        return this;
    }

    public BoundingBoxAttachment setHeight(Float height) {
        this.height = height;
        return this;
    }

    public BoundingBoxAttachment setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
        return this;
    }
}
