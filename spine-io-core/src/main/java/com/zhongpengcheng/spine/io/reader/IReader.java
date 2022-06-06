package com.zhongpengcheng.spine.io.reader;

import java.io.IOException;

/**
 * 骨骼文件读取器抽象接口
 *
 * @author zhongpengcheng
 * @since 2022-01-25 15:42:23
 **/
public interface IReader<T> {
    /**
     * 从输入流中读取指定类型的对象
     * @return 对应对象
     */
    T read() throws IOException;
}
