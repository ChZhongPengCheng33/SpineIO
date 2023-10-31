package com.zhongpengcheng.spine.io;

import cn.hutool.core.io.IoUtil;
import com.sun.istack.internal.Nullable;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.InputStream;

/**
 * SpineIO数据读取流
 *
 * @author skyfire33
 * @since 2022-01-25 15:33:22
 **/
public class SpineIODataInputStream implements AutoCloseable {
    private char[] chars = new char[32];

    private final DataInputStream in;

    public SpineIODataInputStream(InputStream in) {
        this.in = new DataInputStream(in);
    }

    public static SpineIODataInputStream with(byte[] bytes) {
        return new SpineIODataInputStream(IoUtil.toStream(bytes));
    }

    public static SpineIODataInputStream with(ByteArrayOutputStream output) {
        return new SpineIODataInputStream(IoUtil.toStream(output));
    }

    @SneakyThrows
    public boolean readBoolean() {
        return in.readBoolean();
    }

    @SneakyThrows
    public int readInt() {
        return in.readInt();
    }

    @SneakyThrows
    public float readFloat() {
        return in.readFloat();
    }

    /**
     * 读取1-5字节的变长整型
     */
    @SneakyThrows
    public int readInt(boolean optimizePositive) {
        // 读取第一个字节
        int b = in.read();
        // 结果为该字节低7位此时结果为：0b00000000_00000000_00000000_0xxxxxxx
        int result = b & 0b01111111;
        // 如果读取到的字节最高位不为0，则认为有后续值
        if ((b & 0b10000000) != 0) {
            // 读取下一个字节
            b = in.read();
            // 取读取到的字节的低7位并左移7 * 1位
            int tmp = (b & 0b01111111) << 7;
            // 将左移后的数值与上一步得到的result用或连接，此时结果为：0b00000000_00000000_00yyyyyy_yxxxxxxx
            // 即第二个字节的低7位作为int的第二部分
            result |= tmp;
            // 如果当前读取到的字节高位不为0，则认为有后续值
            if ((b & 0b10000000) != 0) {
                // 读取下一个字节
                b = in.read();
                // 取读取到的字节的低7位并左移7 * 2位
                int tmp2 = (b & 0b01111111) << 14;
                // 将左移后的数值与上一步得到的result用或连接，此时结果为：0b00000000_000zzzzz_zzyyyyyy_yxxxxxxx
                result |= tmp2;
                // 如果当前读取到的字节高位不为0，则认为有后续值
                if ((b & 0b10000000) != 0) {
                    // 读取下一个字节
                    b = in.read();
                    // 取读取到的字节的低7位并左移7 * 3位
                    int tmp3 = (b & 0b01111111) << 21;
                    // 将左移后的数值与上一步得到的result用或连接，此时结果为：0b0000XXXX_XXXzzzzz_zzyyyyyy_yxxxxxxx
                    result |= tmp3;
                    // 如果当前读取到的字节高位不为0，则认为有后续值
                    if ((b & 0b10000000) != 0) {
                        // 读取下一个字节
                        b = in.read();
                        // 取读取到的字节的低7位并左移7 * 4位：0b01101111 -> 0bYYYY0000_00000000_00000000_00000000
                        int tmp4 = (b & 0b01111111) << 28;
                        // 将左移后的数值与上一步得到的result用或连接，此时结果为：0bYYYYXXXX_XXXzzzzz_zzyyyyyy_yxxxxxxx
                        result |= tmp4;
                    }
                }
            }
        }
        /*
        optimizePositive为false时：
        1. 取结果的最低位
         */
        return optimizePositive ? result : ((result >>> 1) ^ -(result & 0b00000000_00000000_00000000_00000001));
    }

    /**
     * 读取一个UTF-8字符串
     */
    @SneakyThrows
    @Nullable
    public String readString() {
        int byteCount = readInt(true);
        switch (byteCount) {
            case 0:
                return null;
            case 1:
                return "";
            default:
        }
        byteCount--;
        if (chars.length < byteCount) chars = new char[byteCount];
        char[] chars = this.chars;
        int charCount = 0;
        for (int i = 0; i < byteCount; ) {
            int b = in.read();
            switch (b >> 4) {
                case -1:
                    throw new EOFException();
                case 12:
                case 13:
                    // 读取2字节的字符串
                    chars[charCount++] = (char) ((b & 0b0001_1111) << 6 | in.read() & 0b0011_1111);
                    i += 2;
                    break;
                case 14:
                    // 读取3字节的字符串（例如中文）
                    chars[charCount++] = (char) ((b & 0b0000_1111) << 12
                            | (in.read() & 0b0011_1111) << 6
                            | in.read() & 0b0011_1111);
                    i += 3;
                    break;
                default:
                    chars[charCount++] = (char) b;
                    i++;
            }
        }
        return new String(chars, 0, charCount);
    }

    /**
     * 读取一个UTF8格式的字符串，带默认值
     */
    @SneakyThrows
    public String readString(String defaultValue) {
        String str = this.readString();
        return str == null || str.isEmpty() ? defaultValue : str;
    }

    /**
     * 读取float数组
     */
    @SneakyThrows
    public Float[] readFloatArray(int length) {
        Float[] array = new Float[length];
        for (int i = 0; i < length; i++) {
            array[i] = in.readFloat();
        }
        return array;
    }

    /**
     * 读取长度为n的short数组
     *
     * @param length 数组长度
     */
    @SneakyThrows
    public Short[] readShortArray(int length) {
        Short[] array = new Short[length];
        for (int i = 0; i < length; i++) {
            array[i] = in.readShort();
        }
        return array;
    }

    @SneakyThrows
    public short readShort() {
        return in.readShort();
    }

    @SneakyThrows
    public byte readByte() {
        return in.readByte();
    }

    @SneakyThrows
    @Override
    public void close() {
        in.close();
    }
}
