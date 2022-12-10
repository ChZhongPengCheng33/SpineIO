package com.zhongpengcheng.spine.io.context;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.util.Date;
import java.util.Objects;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 21:06:00
 */
@Getter
@Setter
public class PipelineContext implements Closeable {
    private transient static final Logger log = LoggerFactory.getLogger(PipelineContext.class);
    /**
     * 处理开始时间
     */
    private Date startProcessTime;
    /**
     * 处理结束时间
     */
    private Date endProcessTime;
    /**
     * 骨骼文件路径
     */
    private String skelFilePath;
    /**
     * 获取处理耗时（ms）
     */
    public long getCostTimeMillis() {
        if (ObjectUtil.hasEmpty(startProcessTime, endProcessTime)) return -1;
        return endProcessTime.getTime() - startProcessTime.getTime();
    }

    @Override
    public void close() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
