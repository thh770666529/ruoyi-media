package com.ruoyi.common.exception.file;

public class NotSameFileExpection extends Exception {
    public NotSameFileExpection() {
        super("File MD5 Different");
    }
}
