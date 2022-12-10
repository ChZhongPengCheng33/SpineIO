package com.zhongpengcheng.spine.io.v35.reader;

import cn.hutool.core.lang.Assert;
import com.zhongpengcheng.spine.exception.SpineIOException;
import com.zhongpengcheng.spine.io.v35.Spine35DataInputStream;

public class BinarySkeletonReaderBuilder {
    private Spine35DataInputStream input;

    public static BinarySkeletonReaderBuilder newInstance() {
        return new BinarySkeletonReaderBuilder();
    }

    public BinarySkeletonReaderBuilder input(Spine35DataInputStream input) {
        this.input = input;
        return this;
    }

    public BinarySkeletonReader build() {
        Assert.notNull(input, () -> new SpineIOException("骨骼文件输入流不能为空"));

        return new BinarySkeletonReader(input);
    }
}
