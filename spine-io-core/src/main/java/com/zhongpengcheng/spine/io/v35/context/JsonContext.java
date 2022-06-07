package com.zhongpengcheng.spine.io.v35.context;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author ZhongPengCheng
 * @since 2022-06-07 20:42:00
 */
@Slf4j
@Getter
@Setter
public class JsonContext extends AbstractContext {

    /**
     * JSON对象
     */
    private JSONObject reader;

    /**
     * 静态构造方法
     * @param url 文件路径
     * @return 上下文实例
     */
    public static JsonContext of(String url) {
        JsonContext ctx = new JsonContext();

        ctx.setSkelFilePath(url);
        ctx.setReader(JSONObject.parseObject(FileUtil.readUtf8String(url)));

        return ctx;
    }

    /**
     * 静态构造方法
     * @param file 文件对象
     * @return 上下文实例
     */
    public static JsonContext of(File file) {
        JsonContext ctx = new JsonContext();

        ctx.setSkelFilePath(file.getAbsolutePath());
        ctx.setReader(JSONObject.parseObject(FileUtil.readUtf8String(file)));

        return ctx;
    }

    @Override
    public String toString() {
        return super.toString() + "-JSON骨骼读取上下文";
    }
}
