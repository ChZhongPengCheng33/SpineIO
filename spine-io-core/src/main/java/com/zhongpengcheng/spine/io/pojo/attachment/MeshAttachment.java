package com.zhongpengcheng.spine.io.pojo.attachment;

import com.zhongpengcheng.spine.io.enums.AttachmentType;

/**
 * @author skyfire33
 * @since 2021-03-02 18:48:00
 */
public class MeshAttachment implements IAttachment {
    private String slot;
    private String type = AttachmentType.MESH.getCode();
    private String name;
    private String path;
    private String color;
    private Float width;
    private Float height;
    private String parent;
    private Boolean deform;
    private Float[] uvs;
    private Short[] triangles;
    private Integer hull;
    private Short[] edges;
    private Integer[] bones;
    private Float[] vertices;
    private Integer worldVerticesLength;
    private int slotIndex;

    public MeshAttachment(String slot, String type, String name, String path, String color, Float width, Float height, String parent, Boolean deform, Float[] uvs, Short[] triangles, Integer hull, Short[] edges, Integer[] bones, Float[] vertices, Integer worldVerticesLength, int slotIndex) {
        this.slot = slot;
        this.type = type;
        this.name = name;
        this.path = path;
        this.color = color;
        this.width = width;
        this.height = height;
        this.parent = parent;
        this.deform = deform;
        this.uvs = uvs;
        this.triangles = triangles;
        this.hull = hull;
        this.edges = edges;
        this.bones = bones;
        this.vertices = vertices;
        this.worldVerticesLength = worldVerticesLength;
        this.slotIndex = slotIndex;
    }

    public MeshAttachment() {
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

    public String getPath() {
        return this.path;
    }

    public String getColor() {
        return this.color;
    }

    public Float getWidth() {
        return this.width;
    }

    public Float getHeight() {
        return this.height;
    }

    public String getParent() {
        return this.parent;
    }

    public Boolean getDeform() {
        return this.deform;
    }

    public Float[] getUvs() {
        return this.uvs;
    }

    public Short[] getTriangles() {
        return this.triangles;
    }

    public Integer getHull() {
        return this.hull;
    }

    public Short[] getEdges() {
        return this.edges;
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

    public MeshAttachment setSlot(String slot) {
        this.slot = slot;
        return this;
    }

    public MeshAttachment setType(String type) {
        this.type = type;
        return this;
    }

    public MeshAttachment setName(String name) {
        this.name = name;
        return this;
    }

    public MeshAttachment setPath(String path) {
        this.path = path;
        return this;
    }

    public MeshAttachment setColor(String color) {
        this.color = color;
        return this;
    }

    public MeshAttachment setWidth(Float width) {
        this.width = width;
        return this;
    }

    public MeshAttachment setHeight(Float height) {
        this.height = height;
        return this;
    }

    public MeshAttachment setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public MeshAttachment setDeform(Boolean deform) {
        this.deform = deform;
        return this;
    }

    public MeshAttachment setUvs(Float[] uvs) {
        this.uvs = uvs;
        return this;
    }

    public MeshAttachment setTriangles(Short[] triangles) {
        this.triangles = triangles;
        return this;
    }

    public MeshAttachment setHull(Integer hull) {
        this.hull = hull;
        return this;
    }

    public MeshAttachment setEdges(Short[] edges) {
        this.edges = edges;
        return this;
    }

    public MeshAttachment setBones(Integer[] bones) {
        this.bones = bones;
        return this;
    }

    public MeshAttachment setVertices(Float[] vertices) {
        this.vertices = vertices;
        return this;
    }

    public MeshAttachment setWorldVerticesLength(Integer worldVerticesLength) {
        this.worldVerticesLength = worldVerticesLength;
        return this;
    }

    public MeshAttachment setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
        return this;
    }
}
