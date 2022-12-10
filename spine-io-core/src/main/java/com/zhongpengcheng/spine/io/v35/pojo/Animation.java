package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;
import com.zhongpengcheng.spine.io.v35.pojo.timeline.ITimeline;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 动画
 * @author ZhongPengCheng
 * @since 2022-01-26 20:49:00
 */
@Data
@Builder
public class Animation {
    @Expose
    private Map<String, List<ITimeline>> timelineMap;
    @Expose
    private String name;
}
