package com.zhongpengcheng.spine;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zhongpengcheng.spine.factory.SkeletonSerializeModuleFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * SpineIO门面
 *
 * @author skyfire33
 * @since 2022-12-10 18:40:00
 */
@Slf4j
public class SpineIO {
    @Getter
    @Setter
    private static ObjectMapper objectMapper;
    public static final Version VERSION = new Version(1, 0, 0, null, "com.zhongpengcheng.spine", "spine-io");

    static {
        SimpleModule skelModule = new SkeletonSerializeModuleFactory()
                .createModule("Skeleton Serialize Module", VERSION);
        objectMapper = new ObjectMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(skelModule);
    }
}
