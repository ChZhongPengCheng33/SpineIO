package com.zhongpengcheng.spine.io.pipeline.context;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.zhongpengcheng.spine.io.v35.pojo.*;
import com.zhongpengcheng.spine.io.v35.stream.Spine35DataInputStream;
import com.zhongpengcheng.spine.util.IOUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 21:28:00
 */
@Slf4j
@Getter
@Setter
public class Spine35Context extends PipelineContext {
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
     * 输入流
     */
    private Spine35DataInputStream input;
    /**
     * 是否需读取非必须数据
     */
    private boolean nonessential = false;
    /**
     * 文件头信息
     */
    private Head head;
    /**
     * 节点列表
     */
    private List<Bone> bones = new ArrayList<>(DEFAULT_BONE_SIZE);
    /**
     * 插槽列表
     */
    private List<Slot> slots = new ArrayList<>();
    /**
     * ik约束列表
     */
    private List<Ik> iks = new ArrayList<>();
    /**
     * 旋转列表
     */
    private List<Transform> transforms = new ArrayList<>();
    /**
     * 路径列表
     */
    private List<Path> paths = new ArrayList<>();
    /**
     * 皮肤列表
     */
    private List<Skin> skins = new ArrayList<>();
    /**
     * 事件列表
     */
    private List<Event> events = new ArrayList<>();
    /**
     * 动作列表
     */
    private List<Animation> animations = new ArrayList<>();

    public static Spine35Context of(String url) {
        File file = FileUtil.file(url);
        Spine35DataInputStream stream = new Spine35DataInputStream(IOUtil.inputStreamOf(file));
        return of(stream);
    }

    public static Spine35Context of(Spine35DataInputStream input) {
        Spine35Context ctx = new Spine35Context();
        ctx.setInput(input);
        return ctx;
    }

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
    public void close() {
        try {
            input.close();
            log.debug("[{}]-关闭文件输入流成功", this);
        } catch (IOException e) {
            log.error("[{}]-关闭文件输入流异常: {}", this, e);
        }
    }

    @Override
    public String toString() {
        return "Spine v3.5骨骼读取上下文";
    }
}
