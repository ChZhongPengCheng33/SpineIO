package com.zhongpengcheng.spine.core.spine35.pojo;

import com.zhongpengcheng.spine.core.spine35.pojo.timeline.ITimeline;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * 动画
 *
 * @author skyfire33
 * @since 2022-01-26 20:49:00
 */
@Getter
public class Animation {
    private Map<String, List<ITimeline>> timelineMap;
    private String name;

    public Animation(Map<String, List<ITimeline>> timelineMap, String name) {
        this.timelineMap = timelineMap;
        this.name = name;
    }

    public Animation() {
    }

    public Animation setTimelineMap(Map<String, List<ITimeline>> timelineMap) {
        this.timelineMap = timelineMap;
        return this;
    }

    public Animation setName(String name) {
        this.name = name;
        return this;
    }
}
