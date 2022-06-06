package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.io.SpineDataInputStream;
import com.zhongpengcheng.spine.pojo.*;
import com.zhongpengcheng.spine.util.IOUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * 二进制骨骼文件总读取器
 *
 * @author zhongpengcheng
 * @since 2022-01-25 16:11:36
 **/
@Slf4j
@Getter
@Setter
@Accessors(chain = true)
public class BinaryReader extends AbstractReader<Skeleton> {
    /**
     * 文件头读取器
     */
    private IReader<Head> headReader;
    /**
     * 骨骼节点读取器
     */
    private IReader<List<Bone>> bonesReader;
    /**
     * 骨骼节点读取器
     */
    private IReader<List<Slot>> slotsReader;
    /**
     * 约束读取器
     */
    private IReader<List<Ik>> iksReader;
    /**
     * 变换读取器
     */
    private IReader<List<Transform>> transformsReader;
    /**
     * 路径读取器
     */
    private IReader<List<Path>> pathsReader;
    /**
     * 皮肤读取器
     */
    private IReader<List<Skin>> skinsReader;
    /**
     * 皮肤读取器
     */
    private IReader<List<Animation>> animationReader;
    /**
     * 皮肤读取器
     */
    private IReader<List<Event>> eventsReader;
    /**
     * 骨骼文件对象
     */
    private final Skeleton skeleton = new Skeleton();

    public BinaryReader(SpineDataInputStream input) {
        super(input);
        init();
    }

    public BinaryReader(File file) {
        this(IOUtil.inputStreamOf(file));
    }

    public BinaryReader(String filepath) {
        this(new File(filepath));
    }

    @Override
    public Skeleton read() throws IOException {
        return skeleton
                .setHead(headReader.read())
                .setBones(bonesReader.read())
                .setSlots(slotsReader.read())
                .setIks(iksReader.read())
                .setTransforms(transformsReader.read())
                .setPaths(pathsReader.read())
                .setSkins(skinsReader.read())
                .setEvents(eventsReader.read())
                .setAnimations(animationReader.read());
    }

    public void init() {
        log.debug("开始初始化skel文件各部分的读取器");
        headReader = new HeadReader(input);
        bonesReader = new BonesReader(input, skeleton);
        slotsReader = new SlotsReader(input, skeleton);
        iksReader = new IksReader(input, skeleton);
        transformsReader = new TransformsReader(input, skeleton);
        pathsReader = new PathsReader(input, skeleton);
        skinsReader = new SkinsReader(input, skeleton);
        eventsReader = new EventsReader(input, skeleton);
        animationReader = new AnimationsReader(input, skeleton);
        log.debug("结束初始化skel文件各部分的读取器");
    }
}
