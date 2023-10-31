# Spine骨骼文件读写库（SpineIO）

## 概述

基于Java的Spine骨骼文件读写库，支持以下功能：

- 同版本骨骼文件的`JSON-二进制`格式互转，即
    - `*.skel`->`*.json`
    - `*.json`->`*.skel`
- 跨版本骨骼文件的`JSON-二进制`格式互转，即
    - `v3.5 *.skel`->`v3.6 *.json`
    - `v3.5 *.skel`->`v3.6 *.skel`
    - ......

## 功能开发进度

- [x] `v3.5 skel -> json`，100%
- [ ] `v3.5 json -> skel`，0%
- [ ] `v3.6 skel -> json`，0%
- [ ] `v3.6 json -> skel`，0%
- [ ] ......

## 文件结构解析

![Spine运行时架构](http://esotericsoftware.com/files/runtime-diagram.png)

### 基础数据类型

#### Boolean布尔值

> `boolean`型占用1字节(byte), 值为1代表`true`而0代表`false`.

简单的8位数值，占用1字节。

#### Short短整型

> ```c
> int readShort () {
>   return (readByte() << 8) | readByte();
> }
> ```

`Short`类型为16位数值，占用2字节。

```java
0b1111_0000_1100_0011 = [0b1111_0000, 0b1100_0011]
```

其中高8位储存为第一个字节，低8位储存为第二个字节。

#### Int值

> ```c
> int readInt () {
>    return (readByte() << 24) | (readByte() << 16) | (readByte() << 8) | readByte();
> }
> ```

`int`型为32位数值, 占用4字节。

```java
0b11111111_00000000_11111111_00000000 = [0b11111111, 0b00000000, 0b11111111, 0b00000000]
```

从高到低按每8位为一段，第一段为第一个字节，第二段为第二个字节，以此类推。

#### Varint可变长整型

> ```c
> int readVarint (int/*bool*/optimizePositive) {
>    unsigned char b = readByte();
>    int value = b & 0x7F;
>    if (b & 0x80) {
>       b = readByte();
>       value |= (b & 0x7F) << 7;
>       if (b & 0x80) {
>          b = readByte();
>          value |= (b & 0x7F) << 14;
>          if (b & 0x80) {
>             b = readByte();
>             value |= (b & 0x7F) << 21;
>             if (b & 0x80) value |= (readByte() & 0x7F) << 28;
>          }
>       }
>    }
>    if (!optimizePositive) value = (((unsigned int)value >> 1) ^ -(value & 1));
>    return value;
> }
> ```

可变长整数值,视数值大小占用1-5个字节。

> 一个变长整型(varint)就是一个int, 但是它的存储占用为1到5个字节(取决于数值的大小). varint有两种, `varint+`用于以更小的空间占用来存储的小正值, `varint-`用于小负值(和正值).
>
> 对于varint中的每个字节, 若有额外字节就使用MSB. 若需要存储小负值则使用移位.

##### Varint+类型

`varint+`可变长整数一般用于存储正值（也可存储负值，但固定占用5字节），其将一个整数拆为了4+7+7+7+7（从高到低）最多五个字节，每个字节的高位决定了其左边是否还有剩余的字节，即：

1. 有一个整数`0b01100110_01100110_01100110_01100110`(0b`0110`0110011`0011001`1001100`1100110`)
2. 将其拆分为5块（从高到低）
   1. part1：0b`0110`0000000`0000000`0000000`0000000`
   2. part2：0b`0000`0110011`0000000`0000000`0000000`
   3. part3：0b`0000`0000000`0011001`0000000`0000000`
   4. part4：0b`0000`0000000`0000000`1001100`0000000`
   5. part5：0b`0000`0000000`0000000`0000000`1100110`
3. 将每一块转换为`byte`
   1. `part1 >>> 28`得`0b00000110`（part1没有更左边的字节故高位置0）
   2. `part2 >>> 21 | 0b10000000`得`0b10110011`（part1有值故高位置1）
   3. `part3 >>> 14 | 0b10000000`得`0b10011001`（part2有值故高位置1）
   4. `part4 >>> 07 | 0b10000000`得`0b11001100`（part3有值故高位置1）
   5. `part5 >>> 00 | 0b10000000`得`0b11100110`（part4有值故高位置1）
4. 写入时从低到高写入，即：
   1. 写入part5：`0b11100110`
   2. 写入part4：`0b11001100`
   3. 写入part3：`0b10011001`
   4. 写入part2：`0b10110011`
   5. 写入part1：`0b00000110`

```java
/**
 * 将int数值转换为可变长整型
 *
 * @param num 待转换数值
 * @return 转换后数值拆分数组，长度在1-5之间
 */
public static int[] toPositiveVarint(int num) {
    int[] parts = {num >>> 28, num << 4 >>> 25, num << 11 >>> 25, num << 18 >>> 25, num << 25 >>> 25};
    int startIndex = 0;
    for (int i = 0; i < parts.length; i++) {
        if (parts[i] != 0) {
            startIndex = i;
            break;
        }
    }
    int[] validParts = Arrays.copyOfRange(parts, startIndex, parts.length);
    for (int i = 1; i < validParts.length; i++) {
        validParts[i] = validParts[i] | 0b10000000;
    }
    return validParts;
}
```

##### Varint-类型

`varint-`类型用于存储负值，也可用于存储正值。要存储负值，需先进行负值转化后才能拆为5部分进行写入。

```java
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
```

#### Float浮点数

> `float`型为32位数值, 占用4字节. 根据语言和架构的不同, 这些字节可以合并为一个int, 然后类型转换为一个float值.

32位浮点数占4字节，与`int`类型相同，故Spine将`float`储存为了一个`int`值。

```java
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
```

#### String字符串

> `string`型是以0结尾的变长UTF-8字符串. 若长度为0, 则字符串为null(在大多数情况下,也可当作空白字符串). 若长度为1, 则字符串为空. 其他情况下, 内存占用为`length - 1`字节. 一个UTF-8符可能不止一个字节, 所以`string`可能会占用更多的字节.

Spine中字符串以【字符串Byte长度，字符串Byte数组，`'\0'`】组成。当Byte长度为0时代表`null`，为1时代表空字符串`“”`，其他情况则为字符串字节数+1；Byte数组为字符串UTF8格式编码的字节数组。

```java
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
```

字符串支持中文。

#### Color色彩

Spine的`Color`数据类型在二进制文件中表现为一个4字节的`int`类型，在内存中表现为一个长度为4的`float`数组，从0-3依次为`Red`、`Green`、`Blue`、`Alpha`。

```java
public static float[] intToColor(int color) {
    return new float[] {
            ((color & 0xff000000) >>> 24) / 255f,
            ((color & 0x00ff0000) >>> 16) / 255f,
            ((color & 0x0000ff00) >>> 8) / 255f,
            ((color & 0x000000ff)) / 255f,};
}

public static int colorToInt(float[] rgba) {
    return (int) (rgba[0] * 255f) << 24
            | (int) (rgba[1] * 255f) << 16
            | (int) (rgba[2] * 255f) << 8
            | (int) (rgba[3] * 255f);
}
```

在存储为`int`数值时，`RGBA`每个通道占`8bit`，从高到低乘以`255F`后依次拼接到`int`类型上，相应的从`int`类型读取通道时也需要除以`255F`。

### 复杂数据对象

Spine的复杂数据对象由上述的基础数据类型组合而来，包括以下类别：

- 文件头`head`

- 骨骼`bone`

- 槽位`slot`

- 逆运动学约束`ik constraint`

  - > **Inverse Kinematics Constraint是逆运动学约束**。
    >
    > 逆运动学约束用于确定机器人模型的关节配置，以实现所需的最终效果位置。基于关节之间的转换，在rigidBodyTree机器人模型中指定了机器人运动学约束。还可以指定外部约束，例如摄像机臂的瞄准约束或特定刚体链接上的笛卡尔边界框。使用机械手约束对象和generalizedInverseKinematics对象。inverseKinematics
    > inverseKinematics系统对象创建一个逆运动学（IK）求解器，对于指定的刚体树模型为期望的末端执行器姿势计算各个关节角度。

- 变换约束`transform constraint`

- 路径约束`path constraint`

- 皮肤`skin`

- 事件`event`

- 动画`animation`

- 时间线`timeline`

- 关键帧`key frame`

- 曲线`curve`

## 更新日志

- 2021年3月7日，星期日，Spine Version：v3.5
  - 初始化runtime分支，runtime分支不再依赖`Spring`；
  - 完成v3.5二进制骨骼文件的数据输入流（`DataInputStream`）的编写。
- 2022年2月17日，星期四，Spine Version：v3.5
  - 完成3.5.51.1版本的读取开发
- 2022年6月6日，星期一，Spine Version：v3.5
  - 使用管道模式（pipeline）重构读取逻辑

## 规范

- 所有提交的代码请覆盖单元测试，框架为Mockito4.4.0及JUnit5

## 合作

如有意合作开发此项目，请邮件联系：1565575613@qq.com或提交`pull request`。

当前项目贡献者：

- **危险的秦先生**
  - [B站](https://space.bilibili.com/53857152)
  - [GitHub](https://github.com/ChZhongPengCheng33)
- **凊弦凝绝Official**
  - [B站](https://space.bilibili.com/14435736/)
  - [GitHub](https://github.com/azurlane-doujin)