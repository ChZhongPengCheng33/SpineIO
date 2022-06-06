package com.zhongpengcheng.spine.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author ZhongPengCheng
 * @since 2021-03-03 14:28:00
 */
@Data
@Builder
public class Event {
    String name;
    @Builder.Default
    Integer aInt = 0;
    @Builder.Default
    Float aFloat = 0F;
    @Builder.Default
    String aString = "";
}
