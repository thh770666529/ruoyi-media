package com.ruoyi.common.enums;

/**
 * 用户状态
 *
 * @author ruoyi
 */
public enum VideoStatus
{
    READY_CONVERT("1", "待转换"), CONVERTING("2", "正在转换"),
    CONVERT_FAIL("3", "转换失败"),CONVERT_SUCCESS("4", "转换成功");

    private final String code;
    private final String info;

    VideoStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
