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

### 数据类型解析

#### Boolean布尔值

> `boolean`型占用1字节(byte), 值为1代表`true`而0代表`false`.

简单的8位数值，占用1字节。

#### Short值

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

#### Varint值

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

`varint+`类型的变长整数将一个整数拆为了4+7+7+7+7（从高到低）最多五个字节，每个字节的高位决定了其左边是否还有剩余的字节，即：

1. 有一个整数`0b01100110_01100110_01100110_01100110`(0b`0110`0110011`0011001`1001100`1100110`)
2. 将其拆分为五块（从高到低）
   1. part1：0b`0110`0000000`0000000`0000000`0000000`
   2. part2：0b`0000`0110011`0000000`0000000`0000000`
   3. part3：0b`0000`0000000`0011001`0000000`0000000`
   4. part4：0b`0000`0000000`0000000`1001100`0000000`
   5. part5：0b`0000`0000000`0000000`0000000`1100110`
3. 将每一块转换为`byte`
   1. part1 >>> 28得`0b00000110`（part1没有更左边的字节故高位置0）
   2. part2 >>> 21得`0b10110011`（part1有值故高位置1）
   3. part3 >>> 14得`0b10011001`（part2有值故高位置1）
   4. part4 >>> 07得`0b11001100`（part3有值故高位置1）
   5. part5 >>> 00得`0b11100110`（part4有值故高位置1）

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