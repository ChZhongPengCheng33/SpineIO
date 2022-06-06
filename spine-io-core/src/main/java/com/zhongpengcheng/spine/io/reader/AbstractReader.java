package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.io.SpineDataInputStream;
import com.zhongpengcheng.spine.pojo.Bone;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取器基类
 *
 * @author zhongpengcheng
 * @since 2022-01-25 15:48:39
 **/
public abstract class AbstractReader<T> implements Closeable, IReader<T> {
    /**
     * 当slot没有颜色时的默认值
     */
    protected static final int NO_SLOT_COLOR = -1;
    /**
     * 默认骨骼数量，这是一个由经验值，用于优化ArrayList性能，减少扩容的发生
     */
    protected static final int DEFAULT_BONE_SIZE = 1 << 5;
    /**
     * ik的bendPositive属性的默认值
     */
    protected static final Byte DEFAULT_BEND_POSITIVE = 1;
    /**
     * 骨骼文件读取流
     */
    protected SpineDataInputStream input;

    public AbstractReader(SpineDataInputStream input) {
        this.input = input;
    }

    @Override
    public void close() throws IOException {
        input.close();
    }

    /**
     * 将读取到的int值转换为rgba的值并转换为 #ffaaffaa 的形式
     */
    protected String rgba8888ToHexColor(int value) {
        return new Color(value).toString();
    }

    /**
     * 将读取到的int值转换为rgba的值并转换为{@link Color}对象
     */
    protected Color rgba8888ToColor(int value) {
        return new Color(value);
    }

    /**
     * 将读取到的int值转换为rgba的值并转换为{@link Color}对象
     */
    protected Color rgb8888ToColor(int value) {
        return new Color(
                ((value & 0x00ff0000) >>> 16) / 255f,
                ((value & 0x0000ff00) >>> 8) / 255f,
                ((value & 0x000000ff)) / 255f,
                1F
        );
    }

    /**
     * 将#ffaaddee形式的rgba格式颜色转换为int值
     *
     * @param hexColor 16进制的RGBA颜色字符串，以“#”开头
     */
    protected int hexColorToRgba(String hexColor) {
        if (hexColor == null || "".equals(hexColor) || !hexColor.startsWith("#") || hexColor.length() != 9) {
            throw new IllegalArgumentException("color字符串格式错误：" + hexColor);
        }

        return (Integer.valueOf(hexColor.substring(1, 3), 16) >> 24)
                | (Integer.valueOf(hexColor.substring(3, 5), 16) >> 16)
                | (Integer.valueOf(hexColor.substring(5, 7), 16) >> 8)
                | (Integer.valueOf(hexColor.substring(7, 9), 16));
    }

    protected List<String> readDependBones(List<Bone> bones) throws IOException {
        List<String> dependBones = new ArrayList<>();
        for (int i = 0, boneCount = input.readInt(true); i < boneCount; i++) {
            dependBones.add(bones.get(input.readInt(true)).getName());
        }

        return dependBones;
    }

    @Data
    @Accessors(chain = true)
    public static class Color {
        private float red;
        private float green;
        private float blue;
        private float alpha;

        public Color(float red, float green, float blue, float alpha) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }

        public Color(int color) {
            red = ((color & 0xff000000) >>> 24) / 255f;
            green = ((color & 0x00ff0000) >>> 16) / 255f;
            blue = ((color & 0x0000ff00) >>> 8) / 255f;
            alpha = ((color & 0x000000ff)) / 255f;
        }

        @Override
        public String toString() {
            return Integer.toHexString((int) (red * 255F)) + Integer.toHexString((int) (green * 255F))
                    + Integer.toHexString((int) (blue * 255F)) + Integer.toHexString((int) (alpha * 255F));
        }
    }
}
