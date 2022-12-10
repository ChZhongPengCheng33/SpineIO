package com.zhongpengcheng.spine.io.v35.pojo;

import com.google.gson.annotations.Expose;
import com.zhongpengcheng.spine.io.v35.pojo.attachment.IAttachment;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 皮肤
 *
 * @author zhongpengcheng
 * @since 2022-01-26 15:29:47
 **/
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skin {
    @Expose
    private String name;
    // key: slotName, value: attachments
    @Expose
    private Map<String, List<IAttachment>> attachments;
    private Map<String, IAttachment> attachmentCache;

    public IAttachment attachmentOf(String attachmentName, int slotIndex) {
        if (attachmentCache == null) {
            attachmentCache = new HashMap<>();
            attachments.forEach((skinName, iAttachments) -> iAttachments.forEach(item -> attachmentCache.put(String.valueOf(31 * (31 + item.getAttachmentName().hashCode()) + item.getSlotIndex()), item)));
        }
        return attachmentCache.get(String.valueOf(31 * (31 + attachmentName.hashCode()) + slotIndex));
    }
}
