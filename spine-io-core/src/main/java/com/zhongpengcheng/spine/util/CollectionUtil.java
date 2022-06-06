package com.zhongpengcheng.spine.util;

import java.util.Collection;

/**
 * 集合工具类
 * @author zhongpengcheng
 * @since 2022-02-17 15:55:40
 **/
public class CollectionUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean notEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
