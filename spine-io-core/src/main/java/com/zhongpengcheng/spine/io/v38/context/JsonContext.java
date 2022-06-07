package com.zhongpengcheng.spine.io.v38.context;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhongPengCheng
 * @since 2022-06-07 21:17:00
 */
@Slf4j
@Setter
@Getter
public class JsonContext extends AbstractContext {
    @Override
    public String toString() {
        return super.toString() + "-JSON骨骼读取上下文";
    }
}
