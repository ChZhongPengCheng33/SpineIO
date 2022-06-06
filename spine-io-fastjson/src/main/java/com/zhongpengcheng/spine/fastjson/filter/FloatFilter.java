package com.zhongpengcheng.spine.fastjson.filter;

import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 将浮点数保留两位小数后输出
 * @author zhongpengcheng
 * @since 2022-02-18 15:21:04
 **/
public class FloatFilter implements ValueFilter {
    @Override
    public Object process(Object object, String name, Object value) {
        if (value instanceof Float) return Float.valueOf(String.format("%.2f", value));;
        return value;
    }
}
