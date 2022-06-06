package com.zhongpengcheng.spine.pojo;

import com.zhongpengcheng.spine.pojo.attachment.IAttachment;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 皮肤
 *
 * @author zhongpengcheng
 * @since 2022-01-26 15:29:47
 **/
@Data
@Builder
public class Skin {
    private String name;
    // key: slotName, value: attachments
    private Map<String, List<IAttachment>> attachments;
    private Map<String, IAttachment> attachmentCache;

    public IAttachment attachmentOf(String attachmentName, int slotIndex) {
        if (attachmentCache == null) {
            attachmentCache = new HashMap<>();
            attachments.forEach((skinName, iAttachments) -> {
                iAttachments.forEach(item -> {
                    attachmentCache.put(String.valueOf(31 * (31 + item.getAttachmentName().hashCode()) + item.getSlotIndex()), item);
                });
            });
        }
        return attachmentCache.get(String.valueOf(31 * (31 + attachmentName.hashCode()) + slotIndex));
    }
}
