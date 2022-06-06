package com.zhongpengcheng.spine.util;

import com.zhongpengcheng.spine.io.v35.stream.Spine35DataInputStream;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * IO工具类
 *
 * @author zhongpengcheng
 * @since 2022-01-26 14:10:10
 **/
@Slf4j
public class IOUtil {
    /**
     * 从文件读取spine骨骼文件输入流
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
}
