package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author ZhongPengCheng
 * @since 2021-03-03 14:28:00
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Expose
    String name;
    @Builder.Default
    @Expose
    Integer aInt = 0;
    @Builder.Default
    @Expose
    Float aFloat = 0F;
    @Builder.Default
    @Expose
    String aString = "";
}
