package com.ruoyi.common.enums;

/**
 * 评论状态
 */
public enum CommentStatusEnum {
    VERIFYING("正在审核"),
    APPROVED("审核通过"),
    REJECT("审核失败"),
    DELETED("已删除");
    private String desc;

    CommentStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
