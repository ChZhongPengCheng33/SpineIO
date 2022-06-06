package com.zhongpengcheng.spine.exception;

/**
 * 骨骼读写异常
 *
 * @author zhongpengcheng
 * @since 2022-01-25 15:45:52
 **/
public class SkeletonIOException extends RuntimeException {
    public SkeletonIOException() {
        super();
    }

    public SkeletonIOException(String message) {
        super(message);
    }

    public SkeletonIOException(Throwable cause) {
        super(cause);
    }
}
