package com.zhongpengcheng.spine.core.spine35.pojo;

import com.google.gson.annotations.Expose;
import com.zhongpengcheng.spine.core.spine35.pojo.attachment.IAttachment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 皮肤
 *
 * @author skyfire33
 * @since 2022-01-26 15:29:47
 **/
public class Skin {
    @Expose
    private String name;
    /**
     * key: slotName, value: attachments
     */
    @Expose
    private Map<String, List<IAttachment>> attachments;
    private Map<String, IAttachment> attachmentCache;

    public Skin(String name, Map<String, List<IAttachment>> attachments, Map<String, IAttachment> attachmentCache) {
        this.name = name;
        this.attachments = attachments;
        this.attachmentCache = attachmentCache;
    }

    public Skin() {
    }

    public IAttachment attachmentOf(String attachmentName, int slotIndex) {
        if (attachmentCache == null) {
            attachmentCache = new HashMap<>();
            attachments.forEach((skinName, iAttachments) -> iAttachments.forEach(item -> attachmentCache.put(String.valueOf(31 * (31 + item.getAttachmentName().hashCode()) + item.getSlotIndex()), item)));
        }
        return attachmentCache.get(String.valueOf(31 * (31 + attachmentName.hashCode()) + slotIndex));
    }

    public String getName() {
        return this.name;
    }

    public Map<String, List<IAttachment>> getAttachments() {
        return this.attachments;
    }

    public Map<String, IAttachment> getAttachmentCache() {
        return this.attachmentCache;
    }

    public Skin setName(String name) {
        this.name = name;
        return this;
    }

    public Skin setAttachments(Map<String, List<IAttachment>> attachments) {
        this.attachments = attachments;
        return this;
    }

    public Skin setAttachmentCache(Map<String, IAttachment> attachmentCache) {
        this.attachmentCache = attachmentCache;
        return this;
    }
}
