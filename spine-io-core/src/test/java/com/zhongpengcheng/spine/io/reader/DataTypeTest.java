package com.zhongpengcheng.spine.io.reader;

import cn.hutool.core.io.IoUtil;
import com.zhongpengcheng.spine.io.stream.Spine35DataInputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author zhongpengcheng
 * @since 2023-10-30 20:52:40
 */
public class DataTypeTest {

    @Test
    void testReadBoolean() throws IOException {
        try (Spine35DataInputStream in = withBytes(new byte[]{
                0x00,
                0x01,
                0x00,
                0x00
        })) {
            Assertions.assertFalse(in.readBoolean());
            Assertions.assertTrue(in.readBoolean());
            Assertions.assertFalse(in.readBoolean());
            Assertions.assertFalse(in.readBoolean());
        }
    }

    @Test
    void testReadShort() throws IOException {
        short num = Short.MAX_VALUE;
        short num2 = Short.MIN_VALUE;
        short num3 = 0;
        try (Spine35DataInputStream in = withBytes(new byte[]{
                (byte) ((num & 0xff00) >> 8),
                (byte) ((num & 0x00ff)),
                (byte) ((num2 & 0xff00) >> 8),
                (byte) ((num2 & 0x00ff)),
                (byte) ((num3 & 0xff00) >> 8),
                (byte) ((num3 & 0x00ff)),
        })) {
            Assertions.assertEquals(num, in.readShort());
            Assertions.assertEquals(num2, in.readShort());
            Assertions.assertEquals(num3, in.readShort());
        }
    }

    @Test
    void testReadInt() throws IOException {
        int num = Integer.MAX_VALUE;
        try (Spine35DataInputStream in = withBytes(new byte[]{
                (byte) ((num >>> 24) & 0x000000ff),
                (byte) ((num >>> 16) & 0x000000ff),
                (byte) ((num >>> 8) & 0x000000ff),
                (byte) (num & 0x000000ff)
        })) {
            Assertions.assertEquals(num, in.readInt());
        }
    }

    @Test
    void testReadVarint() throws IOException {
        int num = 1717986918;
        int part1 = num >>> 28;
        int part2 = num << 4 >>> 25;
        int part3 = num << 11 >>> 25;
        int part4 = num << 18 >>> 25;
        int part5 = num << 25 >>> 25;
        int[] writeArray;
        if (part1 != 0) {
            part2 = 0b10000000 | part2;
            part3 = 0b10000000 | part3;
            part4 = 0b10000000 | part4;
            part5 = 0b10000000 | part5;
            writeArray = new int[]{part5, part4, part3, part2, part1};
        } else if (part2 != 0) {
            part3 = 0b10000000 | part3;
            part4 = 0b10000000 | part4;
            part5 = 0b10000000 | part5;
            writeArray = new int[]{part5, part4, part3, part2};
        } else if (part3 != 0) {
            part4 = 0b10000000 | part4;
            part5 = 0b10000000 | part5;
            writeArray = new int[]{part5, part4, part3};
        } else if (part4 != 0) {
            part5 = 0b10000000 | part5;
            writeArray = new int[]{part5, part4};
        } else {
            writeArray = new int[]{part5};
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (int i : writeArray) {
            outputStream.write(i);
        }
        try (Spine35DataInputStream in = Spine35DataInputStream.with(outputStream)) {
            Assertions.assertEquals(num, in.readInt(true));
        }
    }

    @Test
    void testRightBit() {
        int num = Integer.MIN_VALUE;
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toHexString(num));
        System.out.println(Integer.toBinaryString(num >> 2));
        System.out.println(Integer.toBinaryString(num >>> 2));
    }

    private Spine35DataInputStream withBytes(byte[] bytes) {
        return new Spine35DataInputStream(IoUtil.toStream(bytes));
    }
}
