package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.io.SpineIODataInputStream;
import com.zhongpengcheng.spine.util.DataTypeUtils;
import com.zhongpengcheng.spine.util.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static java.lang.Float.floatToRawIntBits;

/**
 * @author zhongpengcheng
 * @since 2023-10-30 20:52:40
 */
public class DataTypeTest {


    @ParameterizedTest
    @ValueSource(bytes = {0x0, 0x1})
    void testReadBoolean(byte num) {
        try (SpineIODataInputStream in = IOUtils.withBytes(new byte[]{num})) {
            Assertions.assertEquals(num > 0, in.readBoolean());
        }
    }

    @ParameterizedTest
    @ValueSource(shorts = {Short.MAX_VALUE, Short.MIN_VALUE, 0})
    void testReadShort(int num) {
        try (SpineIODataInputStream in = IOUtils.withBytes(new byte[]{
                (byte) ((num & 0xff00) >> 8),
                (byte) ((num & 0x00ff)),
        })) {
            Assertions.assertEquals(num, in.readShort());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 370308, -894185})
    void testReadInt(int num) {
        try (SpineIODataInputStream in = IOUtils.withBytes(new byte[]{
                (byte) ((num >>> 24) & 0x000000ff),
                (byte) ((num >>> 16) & 0x000000ff),
                (byte) ((num >>> 8) & 0x000000ff),
                (byte) (num & 0x000000ff)
        })) {
            Assertions.assertEquals(num, in.readInt());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 114514, -829290})
    void testReadVarint(int num) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] varintArray = DataTypeUtils.toPositiveVarint(num);
        for (int i = varintArray.length - 1; i >= 0; i--) {
            outputStream.write(varintArray[i]);
        }

        try (SpineIODataInputStream in = SpineIODataInputStream.with(outputStream)) {
            Assertions.assertEquals(num, in.readInt(true));
        }
    }

    @Test
    void testConvertToNegativeVarint() {
        int[][] dataSource = new int[][]{
                {-1073741824, 2147483647},
                {-64, 127},
                {2147483608, -80},
                {0, 0},
                {-240446, 480891},
        };
        for (int[] tuple : dataSource) {
            Assertions.assertEquals(tuple[1], DataTypeUtils.toNegativeVarint(tuple[0]));
        }
    }

    @ParameterizedTest
    @ValueSource(floats = {Float.MIN_VALUE, Float.MAX_VALUE, 0f, 622.72f})
    void testReadFloat(float num) {
        try (SpineIODataInputStream in = SpineIODataInputStream.with(DataTypeUtils.floatToBytes(num))) {
            Assertions.assertEquals(floatToRawIntBits(num), floatToRawIntBits(in.readFloat()));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"9Y564pL", "frighten", "我是bad man", ""})
    void testReadString(String str) {
        try(SpineIODataInputStream in = IOUtils.withBytes(DataTypeUtils.strToBytes(str))) {
            Assertions.assertEquals(str, in.readString());
        }
    }

    @Test
    void testReadNullString() {
        try(SpineIODataInputStream in = IOUtils.withBytes(DataTypeUtils.strToBytes(null))) {
            Assertions.assertNull(in.readString());
        }
    }

    @ParameterizedTest
    @ValueSource(floats = {0.5f, 0.51f, 0.511f, 0.02f})
    void testReadColor(float color) {
        float[] colors = new float[] {color, color, color, color};

        Assertions.assertArrayEquals(colors, DataTypeUtils.intToColor(DataTypeUtils.colorToInt(colors)), 0.01F);
        System.out.println(DataTypeUtils.colorToInt(colors));
        System.out.println(DataTypeUtils.colorToString(colors));
        System.out.println(Arrays.toString(DataTypeUtils.colorToBytes(colors)));
    }
}
