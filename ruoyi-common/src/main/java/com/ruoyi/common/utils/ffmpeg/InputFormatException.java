package com.ruoyi.common.utils.ffmpeg;


/**
 * @Author tanhuihuang
 * @Description ffmpeg 格式化异常
 * @Date 9:21 2022/8/25
 */
public class InputFormatException extends Exception {

    private static final long serialVersionUID = 1L;


    public InputFormatException() {
    }

    public InputFormatException(String message) {
        super(message);
    }
}
