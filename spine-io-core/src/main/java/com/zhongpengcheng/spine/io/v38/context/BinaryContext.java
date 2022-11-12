package com.zhongpengcheng.spine.io.v38.context;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhongPengCheng
 * @since 2022-06-07 21:16:00
 */
@Slf4j
@Getter
@Setter
public class BinaryContext extends AbstractContext {
    @Override
    public String toString() {
        return super.toString() + "-二进制骨骼读取上下文";
    }
}
