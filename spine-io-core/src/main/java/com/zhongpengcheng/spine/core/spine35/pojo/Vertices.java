package com.zhongpengcheng.spine.core.spine35.pojo;

import lombok.Getter;

/**
 * @author skyfire33
 * @since 2021-03-02 18:50:00
 */
@Getter
public class Vertices {
    
    private Integer[] bones;

    
    private Float[] vertices;

    public Vertices(Integer[] bones, Float[] vertices) {
        this.bones = bones;
        this.vertices = vertices;
    }

    public Vertices() {
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
