package com.zhongpengcheng.spine.core.spine35.reader;

import cn.hutool.core.lang.Assert;
import com.zhongpengcheng.spine.core.spine35.enums.*;
import com.zhongpengcheng.spine.core.spine35.pojo.*;
import com.zhongpengcheng.spine.core.spine35.pojo.attachment.*;
import com.zhongpengcheng.spine.core.spine35.pojo.timeline.*;
import com.zhongpengcheng.spine.core.spine35.stream.Spine35DataInputStream;
import com.zhongpengcheng.spine.exception.SpineIOException;
import com.zhongpengcheng.spine.util.ColorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;

import static java.util.Optional.ofNullable;

/**
 * @author skyfire33
 * @since 2022-12-10 18:40:00
 */
public class BinarySkeletonReader implements Closeable {
    private static final Logger log = LoggerFactory.getLogger(BinarySkeletonReader.class);

    /**
     * 当slot没有颜色时的默认值
     */
    private static final int NO_SLOT_COLOR = -1;
    /**
     * 默认皮肤名称
     */
    private static final String DEFAULT_SKIN = "default";

    /**
     * 是否读取非必须数据
     */
    private boolean nonessential;
    /**
     * 骨骼文件输入流
     */
    private final Spine35DataInputStream input;
    /**
     * 骨骼文件对象
     */
    private final Skeleton skeleton;

    public BinarySkeletonReader(Spine35DataInputStream input) {
        this.input = input;
        this.skeleton = new Skeleton();
    }

    public Skeleton read() {

        readHead();
        readBones();
        readSlots();
        readIks();
        readTransforms();
        readPaths();
        readSkins();
        readEvents();
        readAnimations();

        return skeleton;
    }

    @Override
    public void close() throws IOException {
        input.close();
    }

    private void readHead() {

        try {
            Head head = new Head()
                    .setHash(input.readString(null))
                    .setVersion(input.readString(null))
                    .setWidth(input.readFloat())
                    .setHeight(input.readFloat())
                    .setNonessential(input.readBoolean());

            nonessential = head.getNonessential();

            if (nonessential) {
                head.setFps(input.readFloat())
                        .setImages(input.readString(null));
            }

            skeleton.setHead(head);
        } catch (IOException e) {
            log.error("读取[skeleton]部分时发生异常");
            throw new SpineIOException("读取[skeleton]部分发生异常", e);
        }
    }

    private void readBones() {
        try {
            int boneCount = input.readInt(true);
            List<Bone> bones = new ArrayList<>(boneCount);

            for (int i = 0; i < boneCount; i++) {
                String boneName = input.readString();
                Bone parent = i == 0 ? null : bones.get(input.readInt(true));
                Bone bone = new Bone()
                        .setName(boneName)
                        .setId(i)
                        .setParent(ofNullable(parent).map(Bone::getName).orElse(null))
                        .setRotation(input.readFloat())
                        .setX(input.readFloat())
                        .setY(input.readFloat())
                        .setScaleX(input.readFloat())
                        .setScaleY(input.readFloat())
                        .setShearX(input.readFloat())
                        .setShearY(input.readFloat())
                        .setLength(input.readFloat());

                TransformMode transformMode = TransformMode.values()[input.readInt(true)];
                if (transformMode != TransformMode.NORMAL) {
                    bone.setTransformMode(transformMode.getCode());
                }
                if (nonessential) {
                    bone.setColor(ColorUtils.rgba8888ToHexColor(input.readInt()));
                }

                bones.add(bone);
            }

            skeleton.setBones(bones);
        } catch (IOException e) {
            log.error("读取[bones]部分时发生异常");
            throw new SpineIOException("读取[bones]部分时发生异常", e);
        }
    }

    private void readSlots() {
        try {
            int slotCount = input.readInt(true);
            List<Slot> slots = new ArrayList<>(slotCount);

            for (int i = 0; i < slotCount; i++) {
                Slot slot = new Slot()
                        .setName(input.readString())
                        .setBone(skeleton.getBoneName(input.readInt(true)))
                        .setId(i);
                /*
                注意: 3.5.51.2版本这里多了一个int读取
                int color = input.readInt();
                 */
                int darkColor = input.readInt();
                if (darkColor != NO_SLOT_COLOR) {
                    slot.setColor(ColorUtils.rgba8888ToHexColor(darkColor));
                }
                slot.setAttachment(input.readString());
                BlendMode blendMode = BlendMode.values()[input.readInt(true)];
                if (blendMode != BlendMode.NORMAL) {
                    slot.setBlend(blendMode.getCode());
                }

                slots.add(slot);
            }

            skeleton.setSlots(slots);
        } catch (IOException e) {
            log.error("读取[slots]部分时发生异常");
            throw new SpineIOException("读取[slots]部分时发生异常", e);
        }
    }

    private void readIks() {
        try {
            int ikCount = input.readInt(true);
            List<Ik> iks = new ArrayList<>(ikCount);

            for (int i = 0; i < ikCount; i++) {
                Ik ik = new Ik()
                        .setName(input.readString())
                        .setOrder(input.readInt(true))
                        .setBones(readDependBones())
                        .setTarget(skeleton.getBoneName(input.readInt(true)))
                        .setMix(input.readFloat())
                        .setBendPositive((int) input.readByte());

                iks.add(ik);
            }

            skeleton.setIks(iks);
        } catch (IOException e) {
            log.error("读取[iks]部分时发生异常");
            throw new SpineIOException("读取[iks]部分时发生异常", e);
        }
    }

    private void readTransforms() {
        try {
            int transformCount = input.readInt(true);
            List<Transform> transforms = new ArrayList<>(transformCount);

            for (int i = 0; i < transformCount; i++) {
                Transform transform = new Transform()
                        .setName(input.readString())
                        .setOrder(input.readInt(true))
                        .setBones(readDependBones())
                        .setTarget(skeleton.getBoneName(input.readInt(true)))
                        .setRotation(input.readFloat())
                        .setX(input.readFloat())
                        .setY(input.readFloat())
                        .setScaleX(input.readFloat())
                        .setScaleY(input.readFloat())
                        .setShearY(input.readFloat())
                        .setRotateMix(input.readFloat())
                        .setTranslateMix(input.readFloat())
                        .setScaleMix(input.readFloat())
                        .setShearMix(input.readFloat());

                transforms.add(transform);
            }

            skeleton.setTransforms(transforms);
        } catch (IOException e) {
            log.error("读取[transforms]部分时发生异常");
            throw new SpineIOException("读取[transforms]部分时发生异常", e);
        }
    }

    private void readPaths() {
        try {
            int pathCount = input.readInt(true);
            List<Path> paths = new ArrayList<>(pathCount);

            for (int i = 0; i < pathCount; i++) {
                Path path = new Path()
                        .setName(input.readString())
                        .setOrder(input.readInt(true))
                        .setBones(readDependBones())
                        .setTarget(skeleton.getSlotName(input.readInt(true)))
                        .setPositionMode(PositionMode.values()[input.readInt(true)].getCode())
                        .setSpacingMode(SpacingMode.values()[input.readInt(true)].getCode())
                        .setRotateMode(RotateMode.values()[input.readInt(true)].getCode())
                        .setRotation(input.readFloat())
                        .setPosition(input.readFloat())
                        .setSpacing(input.readFloat())
                        .setRotateMix(input.readFloat())
                        .setTranslateMix(input.readFloat());

                paths.add(path);
            }

            skeleton.setPaths(paths);
        } catch (IOException e) {
            log.error("读取[paths]部分时发生异常");
            throw new SpineIOException("读取[paths]部分时发生异常", e);
        }
    }

    private void readSkins() {
        try {
            List<Skin> skins = new ArrayList<>();

            {
                // #1 read default skin
                Skin skin = readSkin(DEFAULT_SKIN);
                if (skin != null) {
                    skins.add(skin);
                }
            }

            {
                // #2 read another skin
                int skinCount = input.readInt(true);
                for (int i = 0; i < skinCount; i++) {
                    Skin skin = readSkin(input.readString());
                    if (skin != null) {
                        skins.add(skin);
                    }
                }
            }

            skeleton.setSkins(skins);
        } catch (IOException e) {
            log.error("读取[skins]部分时发生异常");
            throw new SpineIOException("读取[skins]部分时发生异常", e);
        }
    }

    private void readEvents() {
        try {
            int eventCount = input.readInt(true);
            List<Event> events = new ArrayList<>();

            for (int i = 0; i < eventCount; i++) {
                Event event = new Event()
                        .setName(input.readString())
                        .setAInt(input.readInt(false))
                        .setAFloat(input.readFloat())
                        .setAString(input.readString());

                events.add(event);
            }

            skeleton.setEvents(events);
        } catch (IOException e) {
            log.error("读取[events]部分时发生异常");
            throw new SpineIOException("读取[events]部分时发生异常", e);
        }
    }

    private void readAnimations() {
        try {
            int animationCount = input.readInt(true);
            List<Animation> animations = new ArrayList<>(animationCount);

            for (int i = 0; i < animationCount; i++) {
                String animationName = input.readString();
                log.debug("read animation: {}", animationName);

                TimelinesReader timelinesReader = new TimelinesReaderBuilder()
                        .input(input)
                        .skeleton(skeleton)
                        .timelines(new LinkedHashMap<>())
                        .build();
                Map<String, List<ITimeline>> timelinesMap = timelinesReader.read();

                Animation animation = new Animation()
                        .setName(animationName)
                        .setTimelineMap(timelinesMap);

                animations.add(animation);
            }

            skeleton.setAnimations(animations);
        } catch (IOException e) {
            log.error("读取[animations]部分时发生异常");
            throw new SpineIOException("读取[animations]部分时发生异常", e);
        }
    }

    private List<String> readDependBones() {
        try {
            int boneCount = input.readInt(true);
            List<String> dependBones = new ArrayList<>(boneCount);

            for (int i = 0; i < boneCount; i++) {
                dependBones.add(skeleton.getBoneName(input.readInt(true)));
            }

            return dependBones;
        } catch (IOException e) {
            log.error("读取[DependBones]部分时发生异常");
            throw new SpineIOException("读取[DependBones]部分时发生异常", e);
        }
    }

    private Skin readSkin(String skinName) throws IOException {
        int slotCount = input.readInt(true);
        if (slotCount == 0) {
            return null;
        }

        // key: slotName, value: attachments
        Map<String, List<IAttachment>> attachmentMap = new LinkedHashMap<>(slotCount);

        for (int i = 0; i < slotCount; i++) {
            int slotIndex = input.readInt(true);
            String slotName = skeleton.getSlotName(slotIndex);
            List<IAttachment> slotAttachments = readAttachments(slotName, slotIndex);
            attachmentMap.put(slotName, slotAttachments);
        }

        return new Skin()
                .setName(skinName)
                .setAttachments(attachmentMap);
    }

    private List<IAttachment> readAttachments(String slotName, int slotIndex) throws IOException {
        AttachmentsReader attachmentsReader = new AttachmentsReaderBuilder()
                .skeleton(skeleton)
                .input(input)
                .slotName(slotName)
                .slotIndex(slotIndex)
                .build();

        return attachmentsReader.read();
    }

    public static class AttachmentsReader {
        private static final Logger log = LoggerFactory.getLogger(AttachmentsReader.class);

        private final Spine35DataInputStream input;
        private final Skeleton skeleton;
        /**
         * 插槽名称
         */
        private final String slotName;
        /**
         * 插槽下标
         */
        private final int slotIndex;

        public AttachmentsReader(Spine35DataInputStream input, Skeleton skeleton, String slotName, int slotIndex) {
            this.input = input;
            this.skeleton = skeleton;
            this.slotName = slotName;
            this.slotIndex = slotIndex;
        }

        public List<IAttachment> read() throws IOException {
            int attachmentCount = input.readInt(true);
            List<IAttachment> attachments = new ArrayList<>(attachmentCount);

            for (int i = 0; i < attachmentCount; i++) {
                String attachmentName = input.readString();
                String name = Optional.ofNullable(input.readString()).orElse(attachmentName);
                AttachmentType type = AttachmentType.values()[input.readByte()];

                log.debug("read [{}] attachment", type);
                switch (type) {
                    case REGION: {
                        IAttachment attachment = readRegionAttachment(name);
                        attachments.add(attachment);
                        break;
                    }
                    case BOUNDING_BOX: {
                        IAttachment attachment = readBoundingBoxAttachment(name);
                        attachments.add(attachment);
                        break;
                    }
                    case MESH: {
                        IAttachment attachment = readMeshAttachment(name);
                        attachments.add(attachment);
                        break;
                    }
                    case LINKED_MESH: {
                        IAttachment attachment = readLinkedMeshAttachment(name);
                        attachments.add(attachment);
                        break;
                    }
                    case PATH: {
                        IAttachment attachment = readPathAttachment(name);
                        attachments.add(attachment);
                        break;
                    }
                    default:
                        log.warn("unknown attachment type: {}", type);
                }
            }

            return attachments;
        }

        private RegionAttachment readRegionAttachment(String attachmentName) throws IOException {
            return new RegionAttachment()
                    .setName(attachmentName)
                    .setPath(Optional.ofNullable(input.readString()).orElse(attachmentName))
                    .setRotation(input.readFloat())
                    .setX(input.readFloat())
                    .setY(input.readFloat())
                    .setScaleX(input.readFloat())
                    .setScaleY(input.readFloat())
                    .setWidth(input.readFloat())
                    .setHeight(input.readFloat())
                    .setColor(ColorUtils.rgba8888ToHexColor(input.readInt()))
                    .setSlot(slotName)
                    .setSlotIndex(slotIndex);
        }

        private BoundingBoxAttachment readBoundingBoxAttachment(String attachmentName) throws IOException {
            int vertexCount = input.readInt(true);
            Vertices vertices = readVertices(vertexCount);
            return new BoundingBoxAttachment()
                    .setWorldVerticesLength(vertexCount << 1)
                    .setVertices(vertices.getVertices())
                    .setBones(vertices.getBones())
                    .setColor(ColorUtils.rgba8888ToHexColor(skeleton.nonessential() ? input.readInt() : 0))
                    .setName(attachmentName)
                    .setSlot(slotName)
                    .setSlotIndex(slotIndex);
        }

        private MeshAttachment readMeshAttachment(String attachmentName) throws IOException {
            String path = Optional.ofNullable(input.readString()).orElse(attachmentName);
            int color = input.readInt();
            int vertexCount = input.readInt(true);
            Float[] uvs = input.readFloatArray(vertexCount << 1);
            Short[] triangles = input.readShortArray(input.readInt(true));
            Vertices vertices = readVertices(vertexCount);
            int hullLength = input.readInt(true);

            Short[] edges = null;
            float width = 0;
            float height = 0;
            if (skeleton.nonessential()) {
                edges = input.readShortArray(input.readInt(true));
                width = input.readFloat();
                height = input.readFloat();
            }

            return new MeshAttachment()
                    .setName(attachmentName)
                    .setPath(path)
                    .setColor(ColorUtils.rgba8888ToHexColor(color))
                    .setBones(vertices.getBones())
                    .setVertices(vertices.getVertices())
                    .setWorldVerticesLength(vertexCount << 1)
                    .setTriangles(triangles)
                    .setUvs(uvs)
                    .setHull(hullLength << 1)
                    .setEdges(edges)
                    .setWidth(width)
                    .setHeight(height)
                    .setType(AttachmentType.MESH.getCode())
                    .setSlot(slotName)
                    .setSlotIndex(slotIndex);
        }

        private MeshAttachment readLinkedMeshAttachment(String attachmentName) throws IOException {
            String path = Optional.ofNullable(input.readString()).orElse(attachmentName);
            int color = input.readInt();
            String skinName = input.readString();
            String parent = input.readString();
            boolean inheritDeform = input.readBoolean();

            float width = 0;
            float height = 0;
            if (skeleton.nonessential()) {
                width = input.readFloat();
                height = input.readFloat();
            }

            return new MeshAttachment()
                    .setPath(path)
                    .setParent(parent)
                    .setColor(ColorUtils.rgba8888ToHexColor(color))
                    .setDeform(inheritDeform)
                    .setWidth(width)
                    .setHeight(height)
                    .setType(AttachmentType.LINKED_MESH.getCode())
                    .setName(attachmentName)
                    .setSlot(slotName)
                    .setSlotIndex(slotIndex);
        }

        private PathAttachment readPathAttachment(String attachmentName) throws IOException {
            boolean closed = input.readBoolean();
            boolean constantSpeed = input.readBoolean();
            int vertexCount = input.readInt(true);
            Vertices vertices = readVertices(vertexCount);
            Float[] lengths = new Float[vertexCount / 3];
            for (int i = 0; i < lengths.length; i++) {
                lengths[i] = input.readFloat();
            }
            int color = skeleton.nonessential() ? input.readInt() : 0;

            return new PathAttachment()
                    .setName(attachmentName)
                    .setClosed(closed)
                    .setConstantSpeed(constantSpeed)
                    .setWorldVerticesLength(vertexCount << 1)
                    .setVertices(vertices.getVertices())
                    .setBones(vertices.getBones())
                    .setLengths(lengths)
                    .setColor(ColorUtils.rgba8888ToHexColor(color))
                    .setType(AttachmentType.PATH.getCode())
                    .setSlot(slotName)
                    .setSlotIndex(slotIndex);
        }

        private Vertices readVertices(int vertexCount) throws IOException {
            int verticesLength = vertexCount << 1;
            Vertices vertices = new Vertices();
            if (!input.readBoolean()) {
                vertices.setVertices(input.readFloatArray(verticesLength));
                return vertices;
            }
            List<Float> weights = new ArrayList<>(verticesLength * 3 * 3);
            List<Integer> bones = new ArrayList<>(verticesLength * 3);
            for (int i = 0; i < vertexCount; i++) {
                int boneCount = input.readInt(true);
                bones.add(boneCount);
                for (int ii = 0; ii < boneCount; ii++) {
                    bones.add(input.readInt(true));
                    weights.add(input.readFloat());
                    weights.add(input.readFloat());
                    weights.add(input.readFloat());
                }
            }

            return vertices
                    .setVertices(weights.toArray(new Float[0]))
                    .setBones(bones.toArray(new Integer[0]));
        }
    }

    public static class AttachmentsReaderBuilder {
        private Spine35DataInputStream input;
        private Skeleton skeleton;
        /**
         * 插槽名称
         */
        private String slotName;
        /**
         * 插槽下标
         */
        private int slotIndex;

        public AttachmentsReaderBuilder input(Spine35DataInputStream input) {
            this.input = input;
            return this;
        }

        public AttachmentsReaderBuilder skeleton(Skeleton skeleton) {
            this.skeleton = skeleton;
            return this;
        }

        public AttachmentsReaderBuilder slotName(String slotName) {
            this.slotName = slotName;
            return this;
        }

        public AttachmentsReaderBuilder slotIndex(int slotIndex) {
            this.slotIndex = slotIndex;
            return this;
        }

        public AttachmentsReader build() {
            Assert.notNull(input, () -> new SpineIOException("input不能为空"));
            Assert.notNull(skeleton, () -> new SpineIOException("skeleton不能为空"));
            Assert.notNull(slotName, () -> new SpineIOException("slotName不能为空"));
            Assert.notNull(slotIndex, () -> new SpineIOException("slotIndex不能为空"));

            return new AttachmentsReader(input, skeleton, slotName, slotIndex);
        }
    }

    public static class TimelinesReader {
        public static final int SLOT_ATTACHMENT = 0;
        public static final int SLOT_COLOR = 1;
        public static final int SLOT_TWO_COLOR = 2;

        public static final int BONE_ROTATE = 0;
        public static final int BONE_TRANSLATE = 1;
        public static final int BONE_SCALE = 2;
        public static final int BONE_SHEAR = 3;

        public static final int PATH_POSITION = 0;
        public static final int PATH_SPACING = 1;
        public static final int PATH_MIX = 2;

        /**
         * 线性曲线
         */
        public static final int CURVE_NONE = 0;
        /**
         * 分段曲线
         */
        public static final int CURVE_STEPPED = 1;
        /**
         * 贝塞尔曲线
         */
        public static final int CURVE_BEZIER = 2;

        public static final String SLOT_KEY = "slots";
        public static final String BONE_KEY = "bones";
        public static final String IK_KEY = "ik";
        public static final String TRANSFORM_KEY = "transform";
        public static final String PATH_KEY = "path";
        public static final String DEFORM_KEY = "deform";
        public static final String DRAW_ORDER_KEY = "drawOrder";
        public static final String EVENT_KEY = "events";

        private final Map<String, List<ITimeline>> timelines;
        private final Spine35DataInputStream input;
        private final Skeleton skeleton;

        public TimelinesReader(Map<String, List<ITimeline>> timelines, Spine35DataInputStream input, Skeleton skeleton) {
            this.timelines = timelines;
            this.input = input;
            this.skeleton = skeleton;
        }

        public Map<String, List<ITimeline>> read() throws IOException {
            timelines.put(SLOT_KEY, new ArrayList<>());
            readSlotTimelines();

            timelines.put(BONE_KEY, new ArrayList<>());
            readBoneTimelines();

            timelines.put(IK_KEY, new ArrayList<>());
            readIkTimelines();

            timelines.put(TRANSFORM_KEY, new ArrayList<>());
            readTransformTimelines();

            timelines.put(PATH_KEY, new ArrayList<>());
            readPathTimelines();

            timelines.put(DEFORM_KEY, new ArrayList<>());
            readDeformTimelines();

            timelines.put(DRAW_ORDER_KEY, new ArrayList<>());
            readDrawOrderTimelines();

            timelines.put(EVENT_KEY, new ArrayList<>());
            readEventTimelines();

            return timelines;
        }

        private void readSlotTimelines() throws IOException {
            int slotCount = input.readInt(true);
            log.debug("read {} slots which has timeline", slotCount);

            for (int i = 0; i < slotCount; i++) {
                int slotIndex = input.readInt(true);
                int timelineCount = input.readInt(true);

                for (int ii = 0; ii < timelineCount; ii++) {
                    // 读取时间线类型：attachment、color（rgba）、two_color（rgb、rgba）
                    int timelineType = input.readByte();
                    // 读取时间线帧数量
                    int frameCount = input.readInt(true);
                    // 拿到插槽名称
                    String slotName = skeleton.getSlotName(slotIndex);
                    log.debug("read slot {}'s timeline, count: {}", slotName, timelineCount);

                    switch (timelineType) {
                        // 读取attachment类型的时间线
                        case SLOT_ATTACHMENT: {
                            AttachmentTimeline timeline = new AttachmentTimeline(frameCount);
                            timeline.setSlotIndex(slotIndex);
                            timeline.setSlotName(slotName);
                            for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                                timeline.addFrame(input.readFloat(), input.readString());
                            }
                            timelines.get(SLOT_KEY).add(timeline);
                            break;
                        }
                        // 读取color类型的时间线
                        case SLOT_COLOR: {
                            ColorTimeline timeline = new ColorTimeline(frameCount);
                            timeline.setSlotIndex(slotIndex);
                            timeline.setSlotName(slotName);
                            for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                                float time = input.readFloat();
                                ColorUtils.Color color = ColorUtils.rgba8888ToColor(input.readInt());
                                timeline.addFrame(time, color);
                                if (frameIndex < frameCount - 1) {
                                    this.readCurve(timeline, frameIndex);
                                }
                            }
                            timelines.get(SLOT_KEY).add(timeline);
                            break;
                        }
                        // 读取two_color类型的时间线
                        case SLOT_TWO_COLOR:
                            TwoColorTimeline timeline = new TwoColorTimeline(frameCount);
                            timeline.setSlotIndex(slotIndex);
                            timeline.setSlotName(slotName);
                            for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                                float time = input.readFloat();
                                ColorUtils.Color light = ColorUtils.rgba8888ToColor(input.readInt());
                                ColorUtils.Color dark = ColorUtils.rgb8888ToColor(input.readInt());
                                timeline.addFrame(time, light, dark);
                                if (frameIndex < frameCount - 1) readCurve(timeline, frameIndex);
                            }
                            timelines.get(SLOT_KEY).add(timeline);
                            break;
                        default:
                            log.error("unknown slot timeline type: {}", timelineType);
                    }
                }
            }
        }

        private void readBoneTimelines() throws IOException {
            int boneCount = input.readInt(true);
            log.debug("read {} bones which has timeline", boneCount);

            for (int i = 0; i < boneCount; i++) {
                // 读取到当前bone的下标，对应skeleton的bones下标
                int boneIndex = input.readInt(true);
                // 读取时间线数量
                int timelineCount = input.readInt(true);
                // 拿到骨骼名称
                String boneName = skeleton.getBoneName(boneIndex);
                log.debug("read bone {}'s timeline, count: {}", boneName, timelineCount);

                for (int ii = 0; ii < timelineCount; ii++) {
                    // 拿到时间线类型：rotate、translate|scale|shear
                    int timelineType = input.readByte();
                    // 读取帧数量
                    int frameCount = input.readInt(true);

                    switch (timelineType) {
                        // 读rotate类型的时间线
                        case BONE_ROTATE: {
                            RotateTimeline timeline = new RotateTimeline(frameCount);
                            timeline.setBoneIndex(boneIndex);
                            timeline.setBoneName(boneName);
                            timeline.setTimelineType("rotate");

                            for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                                timeline.addFrame(input.readFloat(), input.readFloat());
                                if (frameIndex < frameCount - 1) readCurve(timeline, frameIndex);
                            }
                            timelines.get(BONE_KEY).add(timeline);
                            break;
                        }
                        // 读translate|scale|shear类型的时间线
                        case BONE_TRANSLATE:
                        case BONE_SCALE:
                        case BONE_SHEAR: {
                            TranslateTimeline timeline = new TranslateTimeline(frameCount);
                            timeline.setBoneIndex(boneIndex);
                            timeline.setBoneName(boneName);

                            String timelineTypeName = "translate";
                            if (timelineType == BONE_SCALE)
                                timelineTypeName = "scale";
                            else if (timelineType == BONE_SHEAR)
                                timelineTypeName = "shear";
                            timeline.setTimelineType(timelineTypeName);

                            for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                                timeline.addFrame(input.readFloat(), input.readFloat(), input.readFloat());
                                if (frameIndex < frameCount - 1) readCurve(timeline, frameIndex);
                            }
                            timelines.get(BONE_KEY).add(timeline);
                            break;
                        }
                        default:
                            log.error("未知的骨骼时间线（timeline）类型：{}", timelineType);
                    }
                }
            }
        }

        private void readIkTimelines() throws IOException {
            // 读ik约束数量
            int ikCount = input.readInt(true);
            log.debug("read {} iks which has timeline", ikCount);

            for (int i = 0; i < ikCount; i++) {
                // 获取当前ik约束的下标
                int ikIndex = input.readInt(true);
                // 获取时间线帧数量
                int frameCount = input.readInt(true);

                // 拿到对应ik约束的名称
                String ikName = skeleton.getIkName(ikIndex);
                log.debug("read ik {}'s timeline, count: {}", ikName, 1);

                IkTimeline timeline = new IkTimeline(frameCount)
                        .setIkIndex(ikIndex)
                        .setIkName(ikName);

                for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                    timeline.addFrame(input.readFloat(), input.readFloat(), input.readByte());
                    if (frameIndex < frameCount - 1) {
                        readCurve(timeline, frameIndex);
                    }
                }

                timelines.get(IK_KEY).add(timeline);
            }
        }

        private void readTransformTimelines() throws IOException {
            int transformCount = input.readInt(true);
            log.debug("read {} transforms which has timeline", transformCount);

            for (int i = 0; i < transformCount; i++) {
                int transformIndex = input.readInt(true);
                int frameCount = input.readInt(true);
                String transformName = skeleton.getTransformName(transformIndex);
                log.debug("read transform {}'s timeline, count: {}", transformName, transformCount);

                TransformTimeline timeline = new TransformTimeline(frameCount);
                timeline.setTransformIndex(transformIndex);
                timeline.setTransformName(transformName);

                for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                    timeline.addFrame(input.readFloat(), input.readFloat(), input.readFloat(), input.readFloat(),
                            input.readFloat());
                    if (frameIndex < frameCount - 1) readCurve(timeline, frameIndex);
                }

                timelines.get(TRANSFORM_KEY).add(timeline);
            }
        }

        private void readPathTimelines() throws IOException {
            int pathCount = input.readInt(true);
            log.info("read {} paths which has timeline", pathCount);

            for (int i = 0; i < pathCount; i++) {
                // 拿到路径下标
                int pathIndex = input.readInt(true);
                int timelineCount = input.readInt(true);
                Path path = skeleton.getPaths().get(pathIndex);
                log.debug("read path {}'s timeline, count: {}", path.getName(), timelineCount);

                // 循环读取路径的时间线
                for (int ii = 0; ii < timelineCount; ii++) {
                    int timelineType = input.readByte();
                    int frameCount = input.readInt(true);

                    switch (timelineType) {
                        case PATH_POSITION:
                        case PATH_SPACING: {
                            PathPositionTimeline timeline = new PathPositionTimeline(frameCount);
                            if (timelineType == PATH_SPACING) timeline.setTimelineType("spacing");
                            timeline.setPathIndex(pathIndex);
                            timeline.setPathName(path.getName());

                            for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                                timeline.addFrame(input.readFloat(), input.readFloat());
                                if (frameIndex < frameCount - 1) this.readCurve(timeline, frameIndex);
                            }

                            timelines.get(PATH_KEY).add(timeline);

                            break;
                        }
                        case PATH_MIX: {
                            PathMixTimeline timeline = new PathMixTimeline(frameCount);
                            timeline.setPathIndex(pathIndex);
                            timeline.setPathName(path.getName());

                            for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                                timeline.addFrame(input.readFloat(), input.readFloat(), input.readFloat());
                                if (frameIndex < frameCount - 1) this.readCurve(timeline, frameIndex);
                            }

                            timelines.get(PATH_KEY).add(timeline);

                            break;
                        }
                        default:
                            log.error("unknown path timeline type: {}", timelineType);
                    }
                }
            }
        }

        private void readDeformTimelines() throws IOException {
            int deformCount = input.readInt(true);
            log.debug("read {} deforms which has timeline", deformCount);

            for (int i = 0; i < deformCount; i++) {
                Skin skin = skeleton.getSkins().get(input.readInt(true));
                int slotCount = input.readInt(true);

                for (int ii = 0; ii < slotCount; ii++) {
                    int slotIndex = input.readInt(true);
                    int timelineCount = input.readInt(true);

                    for (int iii = 0; iii < timelineCount; iii++) {
                        String attachmentName = input.readString();
                        IAttachment attachment = skin.attachmentOf(attachmentName, slotIndex);

                        boolean weighted = attachment.weighted();
                        float[] vertices = attachment.vertices();
                        int deformLength = weighted ? vertices.length / 3 * 2 : vertices.length;
                        int frameCount = input.readInt(true);

                        DeformTimeline timeline = new DeformTimeline(frameCount);
                        timeline.setSkinIndex(slotIndex);
                        timeline.setAttachmentName(attachmentName);

                        for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                            float time = input.readFloat();
                            float[] deform;
                            int end = input.readInt(true);
                            if (end == 0) {
                                deform = weighted ? new float[deformLength] : vertices;
                            } else {
                                deform = new float[deformLength];
                                int start = input.readInt(true);
                                end += start;
                                for (int v = start; v < end; v++) {
                                    deform[v] = input.readFloat();
                                }
                                if (!weighted) {
                                    for (int v = 0, vn = deform.length; v < vn; v++) {
                                        deform[v] += vertices[v];
                                    }
                                }
                            }
                            timeline.addFrame(time, deform);
                            if (frameIndex < frameCount - 1) {
                                readCurve(timeline, frameIndex);
                            }
                        }

                        timelines.get(DEFORM_KEY).add(timeline);
                    }
                }
            }
        }

        private void readDrawOrderTimelines() throws IOException {
            int frameCount = input.readInt(true);
            if (frameCount < 1) return;

            DrawOrderTimeline timeline = new DrawOrderTimeline(frameCount);

            for (int i = 0; i < frameCount; i++) {
                float time = input.readFloat();
                int offsetCount = input.readInt(true);

                ArrayList<DrawOrderTimeline.Offset> offsets = new ArrayList<>();
                for (int k = 0; k < offsetCount; k++) {
                    int slotIndex = input.readInt(true);
                    int offset = input.readInt(true);
                    String slotName = skeleton.getSlotName(slotIndex);

                    offsets.add(new DrawOrderTimeline.Offset(slotIndex, offset, slotName));
                }

                timeline.addFrame(time, offsets);
            }
            timelines.get(DRAW_ORDER_KEY).add(timeline);
        }

        private void readEventTimelines() throws IOException {
            int frameCount = input.readInt(true);
            if (frameCount < 1) return;

            EventTimeline timeline = new EventTimeline(frameCount);

            for (int i = 0; i < frameCount; i++) {
                float time = input.readFloat();
                int eventIndex = input.readInt(true);
                Event rawEvent = skeleton.getEvents().get(eventIndex);

                timeline.setEventName(rawEvent.getName());

                timeline.addFrame(
                        time,
                        rawEvent.getName(),
                        input.readInt(true),
                        input.readFloat(),
                        input.readBoolean() ? input.readString() : rawEvent.getAString());
            }

            timelines.get(EVENT_KEY).add(timeline);
        }

        /**
         * 读取曲线时间线的曲线数据
         *
         * @param timeline 曲线时间线，继承自{@link CurveTimeline}
         */
        private void readCurve(CurveTimeline timeline, Integer frameIndex) throws IOException {
            byte curveType = input.readByte();
            switch (curveType) {
                case CURVE_NONE:
                    break;
                case CURVE_STEPPED:
                    timeline.setStepped(true);
                    timeline.addFrameIndex(frameIndex);
                    break;
                case CURVE_BEZIER:
                    timeline.setCurve(input.readFloat(), input.readFloat(), input.readFloat(), input.readFloat());
                    timeline.addFrameIndex(frameIndex);
                    break;
                default:
                    // 如果时间线没有曲线，会读取到curveType为0
                    log.debug("unknown curve type: {}", curveType);
            }
        }
    }

    public static class TimelinesReaderBuilder {
        private Map<String, List<ITimeline>> timelines;
        private Spine35DataInputStream input;
        private Skeleton skeleton;

        public TimelinesReaderBuilder timelines(Map<String, List<ITimeline>> timelines) {
            this.timelines = timelines;
            return this;
        }

        public TimelinesReaderBuilder input(Spine35DataInputStream input) {
            this.input = input;
            return this;
        }

        public TimelinesReaderBuilder skeleton(Skeleton skeleton) {
            this.skeleton = skeleton;
            return this;
        }

        public TimelinesReader build() {
            Assert.notNull(timelines, () -> new SpineIOException("timelines不能为空"));
            Assert.notNull(input, () -> new SpineIOException("input不能为空"));
            Assert.notNull(skeleton, () -> new SpineIOException("skeleton不能为空"));

            return new TimelinesReader(timelines, input, skeleton);
        }
    }
}
