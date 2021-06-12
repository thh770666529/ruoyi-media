package com.ruoyi.ufo.exception;

public class ReadException extends RuntimeException{
    public ReadException(Throwable cause) {
        super("文件读取出现了异常", cause);
    }

    public ReadException(String message) {
        super(message);
    }

    public ReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
