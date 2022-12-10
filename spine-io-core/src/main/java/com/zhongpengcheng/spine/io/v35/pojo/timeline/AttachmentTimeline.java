package com.zhongpengcheng.spine.io.v35.pojo.timeline;

import com.google.gson.annotations.Expose;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhongPengCheng
 * @since 2022-01-26 21:10:00
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentTimeline implements ITimeline {
    @Expose
    private int slotIndex;
    @Expose
    private String slotName;
    @Expose
    private List<Frame> frameList;

    public AttachmentTimeline (int frameCount) {
        frameList = new ArrayList<>(frameCount);
    }

    public void addFrame(float time, String attachmentName) {
        frameList.add(Frame.of(time, attachmentName));
    }

    @Data
    @Builder
    public static class Frame {
        @Expose
        private float time;
        @Expose
        private String name;

        public static Frame of(float time, String name) {
            return new Frame(time, name);
        }
    }
}
