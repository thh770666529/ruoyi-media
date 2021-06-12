package com.ruoyi.ufo.exception;

public class DeleteException extends RuntimeException{
    public DeleteException(Throwable cause) {
        super("删除出现了异常", cause);
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

}
