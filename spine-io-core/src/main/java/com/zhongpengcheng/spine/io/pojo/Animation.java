package com.zhongpengcheng.spine.io.pojo;

import com.google.gson.annotations.Expose;
import com.zhongpengcheng.spine.io.pojo.timeline.ITimeline;

import java.util.List;
import java.util.Map;

/**
 * 动画
 *
 * @author skyfire33
 * @since 2022-01-26 20:49:00
 */
public class Animation {
    @Expose
    private Map<String, List<ITimeline>> timelineMap;
    @Expose
    private String name;

    public Animation(Map<String, List<ITimeline>> timelineMap, String name) {
        this.timelineMap = timelineMap;
        this.name = name;
    }

    public Animation() {
    }

    public Map<String, List<ITimeline>> getTimelineMap() {
        return this.timelineMap;
    }

    public String getName() {
        return this.name;
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
