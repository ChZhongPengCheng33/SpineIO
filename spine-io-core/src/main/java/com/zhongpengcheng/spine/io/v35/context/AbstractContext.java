package com.zhongpengcheng.spine.io.v35.context;

import cn.hutool.core.collection.CollectionUtil;
import com.google.gson.annotations.Expose;
import com.zhongpengcheng.spine.io.context.PipelineContext;
import com.zhongpengcheng.spine.io.v35.pojo.*;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ZhongPengCheng
 * @since 2022-06-07 20:45:00
 */
@Getter
@Setter
public abstract class AbstractContext extends PipelineContext {
    private transient static final Logger log = org.slf4j.LoggerFactory.getLogger(AbstractContext.class);

    /**
     * 当slot没有颜色时的默认值
     */
    public static final int NO_SLOT_COLOR = -1;
    /**
     * 默认骨骼数量，这是一个由经验值，用于优化ArrayList性能，减少扩容的发生
     */
    public static final int DEFAULT_BONE_SIZE = 1 << 5;
    /**
     * ik的bendPositive属性的默认值
     */
    public static final Byte DEFAULT_BEND_POSITIVE = 1;
    /**
     * 是否需读取非必须数据
     */
    private boolean nonessential = false;
    /**
     * 文件头信息
     */
    @Expose
    private Head head;
    /**
     * 节点列表
     */
    @Expose
    private List<Bone> bones = new ArrayList<>(DEFAULT_BONE_SIZE);
    /**
     * 插槽列表
     */
    @Expose
    private List<Slot> slots = new ArrayList<>();
    /**
     * ik约束列表
     */
    @Expose
    private List<Ik> iks = new ArrayList<>();
    /**
     * 旋转列表
     */
    @Expose
    private List<Transform> transforms = new ArrayList<>();
    /**
     * 路径列表
     */
    @Expose
    private List<Path> paths = new ArrayList<>();
    /**
     * 皮肤列表
     */
    @Expose
    private List<Skin> skins = new ArrayList<>();
    /**
     * 事件列表
     */
    @Expose
    private List<Event> events = new ArrayList<>();
    /**
     * 动作列表
     */
    @Expose
    private List<Animation> animations = new ArrayList<>();

    /**
     * 获取指定index的bone的名称
     */
    public String getBoneName(int index) {
        return Optional.ofNullable(CollectionUtil.get(this.bones, index)).orElseGet(Bone::new).getName();
    }

    /**
     * 获取指定index的slot的名称
     */
    public String getSlotName(int index) {
        return Optional.ofNullable(CollectionUtil.get(this.slots, index)).orElseGet(Slot::new).getName();
    }

    /**
     * 获取指定index的ik的名称
     */
    public String getIkName(int index) {
        return Optional.ofNullable(CollectionUtil.get(this.iks, index)).orElseGet(Ik::new).getName();
    }

    /**
     * 获取指定index的transform的名称
     */
    public String getTransformName(int index) {
        return Optional.ofNullable(CollectionUtil.get(this.transforms, index)).orElseGet(Transform::new).getName();
    }

    /**
     * 获取指定index的path的名称
     */
    public String getPathName(int index) {
        return Optional.ofNullable(CollectionUtil.get(this.paths, index)).orElseGet(Path::new).getName();
    }

    /**
     * 获取指定index的path
     */
    public Path getPath(int index) {
        return Optional.ofNullable(CollectionUtil.get(this.paths, index)).orElseGet(Path::new);
    }

    @Override
    public String toString() {
        return "Spine 3.5.*";
    }
}
