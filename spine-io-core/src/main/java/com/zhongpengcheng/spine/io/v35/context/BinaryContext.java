package com.zhongpengcheng.spine.io.v35.context;

import cn.hutool.core.io.FileUtil;
import com.zhongpengcheng.spine.io.v35.Spine35DataInputStream;
import com.zhongpengcheng.spine.util.IOUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author ZhongPengCheng
 * @since 2022-06-06 21:28:00
 */
@Slf4j
@Getter
@Setter
public class BinaryContext extends AbstractContext {

    /**
     * 输入流
     */
    private Spine35DataInputStream input;

    public static BinaryContext of(String url) {

        BinaryContext ctx = of(new Spine35DataInputStream(IOUtils.inputStreamOf(url)));

        ctx.setSkelFilePath(url);

        return ctx;
    }

    public static BinaryContext of(Spine35DataInputStream input) {
        BinaryContext ctx = new BinaryContext();
        ctx.setInput(input);
        return ctx;
    }

    @Override
    public void close() {
        try {
            input.close();
            log.debug("[{}]-关闭文件输入流成功", this);
        } catch (IOException e) {
            log.error("[{}]-关闭文件输入流异常: {}", this, e);
        } catch (Throwable e) {
            log.error("[{}]-未知异常: {}", this, e);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "-二进制骨骼读取上下文";
    }
}
