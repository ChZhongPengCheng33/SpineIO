package com.zhongpengcheng.spine.io.v38.context;

import com.zhongpengcheng.spine.io.context.PipelineContext;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhongPengCheng
 * @since 2022-06-07 20:45:00
 */
@Slf4j
@Getter
@Setter
public abstract class AbstractContext extends PipelineContext {
    /**
     * 是否需读取非必须数据
     */
    private boolean nonessential = false;

    @Override
    public String toString() {
        return "Spine 3.8.*";
    }
}
