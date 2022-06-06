package com.zhongpengcheng.spine.pojo;

import com.zhongpengcheng.spine.pojo.timeline.ITimeline;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 动画
 * @author ZhongPengCheng
 * @version 1.0
 * @since 2022-01-26 20:49:00
 */
@Data
@Builder
public class Animation {
    private Map<String, List<ITimeline>> timelineMap;
    private String name;
}
