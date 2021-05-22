package com.ruoyi.common.enums;

/**
 * 电影演员关联类型
 *
 * @author thh
 */
public enum MovieActorType {
    ACTOR("actor"), DIRECTOR("director");

    private final String value;

    MovieActorType(String value) { this.value = value; }

    public String getValue() {
        return value;
    }
}
