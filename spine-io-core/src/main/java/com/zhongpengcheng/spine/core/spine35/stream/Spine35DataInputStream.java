package com.zhongpengcheng.spine.core.spine35.stream;

import cn.hutool.core.io.IoUtil;

import java.io.*;

/**
 * Spine数据读取流
 *
 * @author skyfire33
 * @since 2022-01-25 15:33:22
 **/
public class Spine35DataInputStream extends DataInputStream {
    private char[] chars = new char[32];

    public Spine35DataInputStream(InputStream in) {
        super(in);
    }

    public static Spine35DataInputStream with(byte[] bytes) {
        return new Spine35DataInputStream(IoUtil.toStream(bytes));
    }

    public static Spine35DataInputStream with(ByteArrayOutputStream output) {
        return new Spine35DataInputStream(IoUtil.toStream(output));
    }

    /**
     * Reads a 1-5 byte int.
     */
    public int readInt(boolean optimizePositive) throws IOException {
        // 读取第一个字节
        int b = read();
        // 结果为该字节低7位此时结果为：0b00000000_00000000_00000000_0xxxxxxx
        int result = b & 0b01111111;
        // 如果读取到的字节最高位不为0，则认为有后续值
        if ((b & 0b10000000) != 0) {
            // 读取下一个字节
            b = read();
            // 取读取到的字节的低7位并左移7 * 1位
            int tmp = (b & 0b01111111) << 7;
            // 将左移后的数值与上一步得到的result用或连接，此时结果为：0b00000000_00000000_00yyyyyy_yxxxxxxx
            // 即第二个字节的低7位作为int的第二部分
            result |= tmp;
            // 如果当前读取到的字节高位不为0，则认为有后续值
            if ((b & 0b10000000) != 0) {
                // 读取下一个字节
                b = read();
                // 取读取到的字节的低7位并左移7 * 2位
                int tmp2 = (b & 0b01111111) << 14;
                // 将左移后的数值与上一步得到的result用或连接，此时结果为：0b00000000_000zzzzz_zzyyyyyy_yxxxxxxx
                result |= tmp2;
                // 如果当前读取到的字节高位不为0，则认为有后续值
                if ((b & 0b10000000) != 0) {
                    // 读取下一个字节
                    b = read();
                    // 取读取到的字节的低7位并左移7 * 3位
                    int tmp3 = (b & 0b01111111) << 21;
                    // 将左移后的数值与上一步得到的result用或连接，此时结果为：0b0000XXXX_XXXzzzzz_zzyyyyyy_yxxxxxxx
                    result |= tmp3;
                    // 如果当前读取到的字节高位不为0，则认为有后续值
                    if ((b & 0b10000000) != 0) {
                        // 读取下一个字节
                        b = read();
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
     * Reads the length and string of UTF8 characters, or null.
     *
     * @return May be null.
     */
    public String readString() throws IOException {
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
            int b = read();
            switch (b >> 4) {
                case -1:
                    throw new EOFException();
                case 12:
                case 13:
                    // 读取2字节的字符串
                    chars[charCount++] = (char) ((b & 0b0001_1111) << 6 | read() & 0b0011_1111);
                    i += 2;
                    break;
                case 14:
                    // 读取3字节的字符串（例如中文）
                    chars[charCount++] = (char) ((b & 0b0000_1111) << 12
                            | (read() & 0b0011_1111) << 6
                            | read() & 0b0011_1111);
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
     * 读取一个UTF8格式的字符串
     *
     * @param defaultValue 字符串为空时返回默认值
     * @return 读取到的非空字符串或传入的默认值
     */
    public String readString(String defaultValue) throws IOException {
        String str = this.readString();
        return str == null || str.isEmpty() ? defaultValue : str;
    }

    /**
     * 读取长度为n的float数组
     *
     * @param n 数组长度
     */
    public Float[] readFloatArray(int n) throws IOException {
        Float[] array = new Float[n];
        for (int i = 0; i < n; i++) {
            array[i] = this.readFloat();
        }
        return array;
    }

    /**
     * 读取长度为n的short数组
     *
     * @param n 数组长度
     */
    public Short[] readShortArray(int n) throws IOException {
        Short[] array = new Short[n];
        for (int i = 0; i < n; i++) {
            array[i] = this.readShort();
        }
        return array;
    }

    private void readUtf8Slow(int charCount, int charIndex, int b) throws IOException {
        char[] chars = this.chars;
        while (true) {
            switch (b >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    chars[charIndex] = (char) b;
                    break;
                case 12:
                case 13:
                    chars[charIndex] = (char) ((b & 0x1F) << 6 | read() & 0x3F);
                    break;
                case 14:
                    chars[charIndex] = (char) ((b & 0x0F) << 12 | (read() & 0x3F) << 6 | read() & 0x3F);
                    break;
                default:
            }
            if (++charIndex >= charCount) {
                break;
            }
            b = read() & 0xFF;
        }
    }
}
