package com.ruoyi.common.enums;

/**
 * 积分类型
 */
public enum IntegralTypeEnum {

    /**
     * 积分补偿
     */
    Compensation(99,"积分补偿",0,0),

    /**
     * 签到
     */
    SignIn(1, "每日签到", 5, 1),

    /**
     * 文章
     */
    Article(2, "创作文章",5, 5);

    /**
     * 积分号
     */
    private final int code;

    /**
     * 积分名称
     */
    private final String name;


    /**
     * 积分
     */
    private final Integer integral;

    /**
     * 每天限制次数
     */
    private final Integer limit;

    IntegralTypeEnum(int code, String name, Integer integral, Integer limit) {
        this.code = code;
        this.name = name;
        this.integral = integral;
        this.limit = limit;
    }


    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getIntegral() {
        return integral;
    }

    public Integer getLimit() {
        return limit;
    }
}
