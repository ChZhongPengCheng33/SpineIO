package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.enums.AttachmentType;
import com.zhongpengcheng.spine.io.SpineDataInputStream;
import com.zhongpengcheng.spine.pojo.Skeleton;
import com.zhongpengcheng.spine.pojo.Skin;
import com.zhongpengcheng.spine.pojo.Vertices;
import com.zhongpengcheng.spine.pojo.attachment.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

/**
 * 皮肤读取器
 *
 * @author zhongpengcheng
 * @since 2022-01-26 15:02:16
 **/
public class SkinsReader extends AbstractReader<List<Skin>> {

    /**
     * 默认皮肤名称
     */
    private static final String DEFAULT_SKIN = "default";

    private final Skeleton skeleton;

    public SkinsReader(SpineDataInputStream input, Skeleton skeleton) {
        super(input);
        this.skeleton = skeleton;
    }

    @Override
    public List<Skin> read() throws IOException {
        List<Skin> skins = new ArrayList<>();

        Skin defaultSkin = readDefaultSkin();
        if (defaultSkin != null) skins.add(defaultSkin);

        List<Skin> anotherSkins = readAnotherSkin();
        skins.addAll(anotherSkins);

        return skins;
    }

    /**
     * 读默认皮肤
     */
    private Skin readDefaultSkin() throws IOException {
        return this.readSkin(DEFAULT_SKIN);
    }

    /**
     * 读其他皮肤
     */
    private List<Skin> readAnotherSkin() throws IOException {
        List<Skin> skins = new LinkedList<>();
        for (int i = 0, skinCount = input.readInt(true); i < skinCount; i++) {
            Skin skin = readSkin(input.readString());
            if (skin != null) skins.add(skin);
        }
        return skins;
    }

    private Skin readSkin(String skinName) throws IOException  {
        int slotCount = input.readInt(true);
        if (slotCount == 0) {
            return null;
        }

        // key: slotName, value: attachments
        Map<String, List<IAttachment>> attachments = new LinkedHashMap<>();

        for (int i = 0; i < slotCount; i++) {
            int slotIndex = input.readInt(true);
            String slotName = skeleton.getSlots().get(slotIndex).getName();
            List<IAttachment> slotAttachment = new AttachmentsReader(input, skeleton, slotName, slotIndex).read();
            attachments.put(slotName, slotAttachment);
        }

        return Skin.builder()
                .name(skinName)
                .attachments(attachments)
                .build();
    }

    @Slf4j
    public static class AttachmentsReader extends AbstractReader<List<IAttachment>> {
        private final Skeleton skeleton;
        private final String slotName;
        private final int slotIndex;

        public AttachmentsReader(SpineDataInputStream input, Skeleton skeleton, String slotName, int slotIndex) {
            super(input);
            this.skeleton = skeleton;
            this.slotName = slotName;
            this.slotIndex = slotIndex;
        }

        @Override
        public List<IAttachment> read() throws IOException {
            List<IAttachment> attachments = new LinkedList<>();
            for (int j = 0, attachmentCount = input.readInt(true); j < attachmentCount; j++) {
                String attachmentName = input.readString();
                String name = Optional.ofNullable(input.readString()).orElse(attachmentName);
                AttachmentType type = AttachmentType.values()[input.readByte()];
                log.debug("读取到{}类型的附件", type);
                switch (type) {
                    case REGION:
                        attachments.add(this.readRegionAttachment(name));
                        break;
                    case BOUNDING_BOX:
                        attachments.add(this.readBoundingBoxAttachment(name));
                        break;
                    case MESH:
                        attachments.add(this.readMeshAttachment(name));
                        break;
                    case LINKED_MESH:
                        attachments.add(this.readLinkedMeshAttachment(name));
                        break;
                    case PATH:
                        attachments.add(this.readPathAttachment(name));
                        break;
                    default:
                }
            }

            return attachments;
        }

        private RegionAttachment readRegionAttachment(String attachmentName) throws IOException {
            return RegionAttachment.builder()
                    .name(attachmentName)
                    .path(Optional.ofNullable(input.readString()).orElse(attachmentName))
                    .rotation(input.readFloat())
                    .x(input.readFloat())
                    .y(input.readFloat())
                    .scaleX(input.readFloat())
                    .scaleY(input.readFloat())
                    .width(input.readFloat())
                    .height(input.readFloat())
                    .color(super.rgba8888ToHexColor(input.readInt()))
                    .slot(slotName)
                    .slotIndex(this.slotIndex)
                    .build();
        }

        private BoundingBoxAttachment readBoundingBoxAttachment(String attachmentName) throws IOException {
            int vertexCount = input.readInt(true);
            Vertices vertices = this.readVertices(vertexCount);
            return BoundingBoxAttachment.builder()
                    .worldVerticesLength(vertexCount << 1)
                    .vertices(vertices.getVertices())
                    .bones(vertices.getBones())
                    .color(rgba8888ToHexColor(skeleton.nonessential() ? input.readInt() : 0))
                    .name(attachmentName)
                    .slot(slotName)
                    .slotIndex(this.slotIndex)
                    .build();
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

            return MeshAttachment.builder()
                    .name(attachmentName)
                    .path(path)
                    .color(rgba8888ToHexColor(color))
                    .bones(vertices.getBones())
                    .vertices(vertices.getVertices())
                    .worldVerticesLength(vertexCount << 1)
                    .triangles(triangles)
                    .uvs(uvs)
                    .hull(hullLength << 1)
                    .edges(edges)
                    .width(width)
                    .height(height)
                    .type(AttachmentType.MESH.getCode())
                    .slot(slotName)
                    .slotIndex(this.slotIndex)
                    .build();
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

            return MeshAttachment.builder()
                    .path(path)
                    .parent(parent)
                    .color(rgba8888ToHexColor(color))
                    .deform(inheritDeform)
                    .width(width)
                    .height(height)
                    .type(AttachmentType.LINKED_MESH.getCode())
                    .name(attachmentName)
                    .slot(slotName)
                    .slotIndex(this.slotIndex)
                    .build();
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

            return PathAttachment.builder()
                    .name(attachmentName)
                    .closed(closed)
                    .constantSpeed(constantSpeed)
                    .worldVerticesLength(vertexCount << 1)
                    .vertices(vertices.getVertices())
                    .bones(vertices.getBones())
                    .lengths(lengths)
                    .color(rgba8888ToHexColor(color))
                    .type(AttachmentType.PATH.getCode())
                    .slot(slotName)
                    .slotIndex(this.slotIndex)
                    .build();
        }

        private Vertices readVertices(int vertexCount) throws IOException {
            int verticesLength = vertexCount << 1;
            Vertices.VerticesBuilder builder = Vertices.builder();
            if (!input.readBoolean()) {
                builder.vertices(input.readFloatArray(verticesLength));
                return builder.build();
            }
            ArrayList<Float> weights = new ArrayList<>(verticesLength * 3 * 3);
            ArrayList<Integer> bones = new ArrayList<>(verticesLength * 3);
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

            return builder.vertices(weights.toArray(new Float[0]))
                    .bones(bones.toArray(new Integer[0]))
                    .build();
        }
    }
}
