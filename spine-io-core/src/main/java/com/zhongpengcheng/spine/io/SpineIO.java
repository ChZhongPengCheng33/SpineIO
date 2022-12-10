package com.zhongpengcheng.spine.io;

import com.zhongpengcheng.spine.io.pojo.Skeleton;
import com.zhongpengcheng.spine.io.stream.Spine35DataInputStream;
import com.zhongpengcheng.spine.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author skyfire33
 * @since 2022-12-10 18:40:00
 */
public class SpineIO {
    private static final Logger log = LoggerFactory.getLogger(SpineIO.class);

    public static Skeleton fromBin(File file) {
        Spine35DataInputStream ins = IOUtils.inputStreamOf(file);
        return null;
    }

    public static File toBin() {
        return null;
    }

    public static Skeleton fromJson(File file) {
        return null;
    }

    public static File toJson() {
        return null;
    }

    public static String toJson(boolean pretty) {
        return null;
    }
}
