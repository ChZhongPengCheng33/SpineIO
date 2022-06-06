package com.zhongpengcheng.spine.io.reader;

import com.zhongpengcheng.spine.io.SpineDataInputStream;
import com.zhongpengcheng.spine.io.reader.AbstractReader;
import com.zhongpengcheng.spine.pojo.Head;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * head读取器
 *
 * @author zhongpengcheng
 * @since 2022-01-25 15:40:03
 **/
@Slf4j
public class HeadReader extends AbstractReader<Head> {

    public HeadReader(SpineDataInputStream input) {
        super(input);
    }

    @Override
    public Head read() throws IOException {
        Head.HeadBuilder builder = Head.builder()
                .hash(input.readString(null))
                .version(input.readString(null))
                .width(input.readFloat())
                .height(input.readFloat());

        boolean nonessential = input.readBoolean();

        if (nonessential) {
            builder.nonessential(true)
                    .fps(input.readFloat())
                    .images(input.readString(null));
        }

        return builder.build();
    }
}
