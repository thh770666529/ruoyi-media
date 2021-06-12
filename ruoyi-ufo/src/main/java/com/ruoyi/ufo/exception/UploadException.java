package com.ruoyi.ufo.exception;

public class UploadException extends RuntimeException{
    public UploadException(Throwable cause) {
        super("上传出现了异常", cause);
    }

    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }

}
