package com.zhongpengcheng.spine.util;

import cn.hutool.core.util.ArrayUtil;
import com.sun.istack.internal.Nullable;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 数据类型工具类
 *
 * @author zhongpengcheng
 * @since 2023-10-31 10:39:40
 */
@Slf4j
public class DataTypeUtils {
    /**
     * 将int数值转换为二进制字符串并填充至32位，高位补0
     *
     * @param num int数值
     * @return 填充到32位后的二进制字符串
     */
    public static String leftPadding(int num) {
        return leftPadding(Integer.toBinaryString(num), '0', 32);
    }

    /**
     * 字符串左填充
     *
     * @param source        待填充源字符串
     * @param paddingChar   填充字符
     * @param paddingLength 填充到的期望长度
     * @return 源字符串null或者长度超过期望长度时，原路返回，否则返回填充后字符串
     */
    public static String leftPadding(String source, Character paddingChar, int paddingLength) {
        if (source == null || source.length() > paddingLength) {
            return source;
        }
        StringBuilder builder = new StringBuilder(paddingLength);
        for (int i = 0; i < paddingLength - source.length(); i++) {
            builder.append(paddingChar);
        }
        builder.append(source);

        return builder.toString();
    }

    /**
     * 将int数值转换为Spine的varint-数值
     *
     * @param num 待转换数值
     * @return 转换后数值
     */
    public static int toNegativeVarint(int num) {
        int symbol = num >>> 31;
        return ((num ^ -symbol) << 1) | symbol;
    }

    /**
     * 将int数值转换为可变长整型
     *
     * @param num 待转换数值
     * @return 转换后数值拆分数组，长度在1-5之间
     */
    public static byte[] toPositiveVarint(int num) {
        int[] parts = {num >>> 28, num << 4 >>> 25, num << 11 >>> 25, num << 18 >>> 25, num << 25 >>> 25};
        int startIndex = 0;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i] != 0) {
                startIndex = i;
                break;
            }
        }
        int[] validParts = Arrays.copyOfRange(parts, startIndex, parts.length);
        byte[] ret = new byte[validParts.length];
        ret[0] = (byte) validParts[0];
        for (int i = 1; i < validParts.length; i++) {
            ret[i] = (byte) (validParts[i] | 0b10000000);
        }
        return ret;
    }

    public static byte[] intToBytes(int num) {
        return new byte[]{
                (byte) ((num >>> 24) & 0x000000ff),
                (byte) ((num >>> 16) & 0x000000ff),
                (byte) ((num >>> 8) & 0x000000ff),
                (byte) (num & 0x000000ff),};
    }

    public static int floatToInt(float num) {
        return Float.floatToRawIntBits(num);
    }

    public static byte[] floatToBytes(float num) {
        return intToBytes(floatToInt(num));
    }

    public static byte[] strToBytes(String str) {
        if (str == null) {
            return toPositiveVarint(0);
        }
        if (str.isEmpty()) {
            return toPositiveVarint(1);
        }
        byte[] charBytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] lengthBytes = toPositiveVarint(charBytes.length + 1);
        return ArrayUtil.addAll(lengthBytes, charBytes);
    }

    public static byte[] colorToBytes(float[] rgba) {
        int color = colorToInt(rgba);
        return intToBytes(color);
    }

    public static int colorToInt(float[] rgba) {
        return (int) (rgba[0] * 255f) << 24
                | (int) (rgba[1] * 255f) << 16
                | (int) (rgba[2] * 255f) << 8
                | (int) (rgba[3] * 255f);
    }

    public static String colorToString(float[] rgba) {
        return leftPadding(Integer.toHexString(colorToInt(rgba)), '0', 8);
    }

    public static String intToHexColor(int color) {
        return colorToString(intToColor(color));
    }

    public static float[] intToColor(int color) {
        return new float[] {
                ((color & 0xff000000) >>> 24) / 255f,
                ((color & 0x00ff0000) >>> 16) / 255f,
                ((color & 0x0000ff00) >>> 8) / 255f,
                ((color & 0x000000ff)) / 255f,};
    }

    @Nullable
    public static float[] strToColor(String hexColor) {
        if (hexColor == null || hexColor.isEmpty()) {
            return null;
        }
        if (hexColor.startsWith("#")) {
            hexColor = hexColor.replaceFirst("#", "");
        }
        if (hexColor.length() != 8) {
            return null;
        }

        try {
            int intColor = Integer.parseInt(hexColor, 16);
            return intToColor(intColor);
        } catch (Throwable e) {
            log.error("parse hex color error", e);
            return null;
        }
    }
}
