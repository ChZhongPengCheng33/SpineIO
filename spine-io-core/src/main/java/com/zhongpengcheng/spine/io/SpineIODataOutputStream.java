package com.zhongpengcheng.spine.io;

import com.zhongpengcheng.spine.util.DataTypeUtils;
import lombok.SneakyThrows;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * SpineIO数据输出流
 *
 * @author zhongpengcheng
 * @since 2023-10-31 14:52:45
 */
public class SpineIODataOutputStream implements AutoCloseable {

    private final DataOutputStream out;

    public static SpineIODataOutputStream with(OutputStream out) {
        return new SpineIODataOutputStream(out);
    }

    public SpineIODataOutputStream(OutputStream out) {
        this.out = new DataOutputStream(out);
    }

    @SneakyThrows
    public SpineIODataOutputStream writeBoolean(boolean bool) {
        out.writeBoolean(bool);
        return this;
    }

    @SneakyThrows
    public SpineIODataOutputStream writeShort(short num) {
        out.writeShort(num);
        return this;
    }

    @SneakyThrows
    public SpineIODataOutputStream writeInt(int num) {
        byte[] bytes = DataTypeUtils.intToBytes(num);
        out.write(bytes);
        return this;
    }

    @SneakyThrows
    public SpineIODataOutputStream writeVarInt(int num, boolean optimize) {
        if (optimize) {
            out.write(DataTypeUtils.toPositiveVarint(num));
        } else {
            out.write(DataTypeUtils.toNegativeVarint(num));
        }
        return this;
    }

    @SneakyThrows
    public SpineIODataOutputStream writeFloat(float num) {
        byte[] bytes = DataTypeUtils.floatToBytes(num);
        out.write(bytes);
        return this;
    }

    @SneakyThrows
    public SpineIODataOutputStream writeString(String str) {
        byte[] bytes = DataTypeUtils.strToBytes(str);
        out.write(bytes);
        return this;
    }

    @SneakyThrows
    public SpineIODataOutputStream writeColor(float[] color) {
        byte[] bytes = DataTypeUtils.colorToBytes(color);
        out.write(bytes);
        return this;
    }

    @SneakyThrows
    @Override
    public void close() {
        out.close();
    }
}
