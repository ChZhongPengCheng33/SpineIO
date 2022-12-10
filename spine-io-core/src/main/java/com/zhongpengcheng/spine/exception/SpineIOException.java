package com.zhongpengcheng.spine.exception;

/**
 * 骨骼读写异常
 *
 * @author skyfire33
 * @since 2022-01-25 15:45:52
 **/
public class SpineIOException extends RuntimeException {
    public SpineIOException() {
        super();
    }

    public SpineIOException(String message) {
        super(message);
    }

    public SpineIOException(Throwable cause) {
        super(cause);
    }

    public SpineIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
