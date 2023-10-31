package com.zhongpengcheng.spine.core.spine35.reader;

import cn.hutool.core.lang.Assert;
import com.zhongpengcheng.spine.exception.SpineIOException;
import com.zhongpengcheng.spine.io.SpineIODataInputStream;

/**
 * @author skyfire33
 * @since 2022-12-10 18:40:00
 */
public class BinarySkeletonReaderBuilder {
    private SpineIODataInputStream input;

    public static BinarySkeletonReaderBuilder newInstance() {
        return new BinarySkeletonReaderBuilder();
    }

    public BinarySkeletonReaderBuilder input(SpineIODataInputStream input) {
        this.input = input;
        return this;
    }

    public BinarySkeletonReader build() {
        Assert.notNull(input, () -> new SpineIOException("骨骼文件输入流不能为空"));

        return new BinarySkeletonReader(input);
    }
}
