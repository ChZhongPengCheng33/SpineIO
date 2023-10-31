package com.zhongpengcheng.spine.core.spine35.pojo;

import com.google.gson.annotations.Expose;

/**
 * @author skyfire33
 * @since 2021-03-02 18:50:00
 */
public class Vertices {
    @Expose
    private Integer[] bones;

    @Expose
    private Float[] vertices;

    public Vertices(Integer[] bones, Float[] vertices) {
        this.bones = bones;
        this.vertices = vertices;
    }

    public Vertices() {
    }

    public Integer[] getBones() {
        return this.bones;
    }

    public Float[] getVertices() {
        return this.vertices;
    }

    public Vertices setBones(Integer[] bones) {
        this.bones = bones;
        return this;
    }

    public Vertices setVertices(Float[] vertices) {
        this.vertices = vertices;
        return this;
    }
}
