package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.io.SpineDataInputStream;
import com.zhongpengcheng.spine.io.reader.AbstractReader;
import com.zhongpengcheng.spine.pojo.Event;
import com.zhongpengcheng.spine.pojo.Skeleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 事件读取器
 *
 * @author zhongpengcheng
 * @since 2022-01-26 17:52:39
 **/
public class EventsReader extends AbstractReader<List<Event>> {

    private final Skeleton skeleton;

    public EventsReader(SpineDataInputStream input, Skeleton skeleton) {
        super(input);
        this.skeleton = skeleton;
    }

    @Override
    public List<Event> read() throws IOException {
        List<Event> events = new ArrayList<>();
        for (int i = 0, n = input.readInt(true); i < n; i++) {
            Event event = Event.builder()
                    .name(input.readString())
                    .aInt(input.readInt(false))
                    .aFloat(input.readFloat())
                    .aString(input.readString())
                    .build();

            events.add(event);
        }

        return events;
    }
}
