package com.zhongpengcheng.spine.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.zhongpengcheng.spine.core.spine35.stream.Spine35DataInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * IO工具类
 *
 * @author skyfire33
 * @since 2022-01-26 14:10:10
 **/
public class IOUtils {
    private static final Logger log = LoggerFactory.getLogger(IOUtils.class);

    /**
     * 从文件读取spine骨骼文件输入流
     *
     * @param file 目标文件
     * @return 可能为空
     */
    public static Spine35DataInputStream inputStreamOf(File file) {
        try {
            return new Spine35DataInputStream(new BufferedInputStream(new FileInputStream(file), 512));
        } catch (FileNotFoundException e) {
            log.error("从文件读取流异常：" + file.getName(), e);
        }

        return null;
    }

    /**
     * 从指定路径读取spine骨骼文件输入流
     *
     * @param url 文件路径
     * @return 可能为空
     */
    public static Spine35DataInputStream inputStreamOf(String url) {
        return inputStreamOf(FileUtil.file(url));
    }

    /**
     * 从字节数组构造输入流
     *
     * @param bytes 字节数组
     * @return Spine输入流
     */
    public static Spine35DataInputStream withBytes(byte[] bytes) {
        return new Spine35DataInputStream(IoUtil.toStream(bytes));
    }
}
