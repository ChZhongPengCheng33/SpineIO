package com.zhongpengcheng.spine.io.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.zhongpengcheng.spine.io.pojo.Head;
import com.zhongpengcheng.spine.io.pojo.Skeleton;

import java.io.IOException;

public class SkeletonAdapter extends TypeAdapter<Skeleton> {

    @Override
    public void write(JsonWriter out, Skeleton skeleton) throws IOException {
        out.beginObject();
        Head head = skeleton.getHead();
        out.name("skeleton")
                .beginObject()
                .name("hash").value(head.getHash())
                .name("spine").value(head.getVersion())
                .name("width").value(head.getWidth())
                .name("height").value(head.getHeight())
                // nonessential property
                .name("fps").value(head.getFps())
                .name("images").value(head.getImages())
                .endObject();

        out.endObject();
    }

    @Override
    public Skeleton read(JsonReader in) throws IOException {
        return null;
    }

    public static SkeletonAdapter singleton() {
        return InstanceHolder.instance;
    }

    private Float format(Float value) {
        return Float.parseFloat(String.format("%.2f", value));
    }

    public static class InstanceHolder {
        public static final SkeletonAdapter instance;

        static {
            instance = new SkeletonAdapter();
        }
    }
}
