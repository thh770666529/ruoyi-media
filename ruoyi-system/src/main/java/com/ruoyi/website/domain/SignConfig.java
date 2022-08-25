package com.ruoyi.website.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 签到配置对象 website_sign_config
 *
 * @author thh
 * @date 2021-12-11
 */
public class SignConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long signConfigId;

    /**
     * 签到天数
     */
    @Excel(name = "签到天数")
    private Long signDay;

    /**
     * 明细种类
     */
    @Excel(name = "明细种类")
    private Long type;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private Long number;

    /**
     * 奖励方式
     */
    @Excel(name = "奖励方式")
    private Integer signSet;

    public void setSignConfigId(Long signConfigId) {
        this.signConfigId = signConfigId;
    }

    public Long getSignConfigId() {
        return signConfigId;
    }

    public void setSignDay(Long signDay) {
        this.signDay = signDay;
    }

    public Long getSignDay() {
        return signDay;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getType() {
        return type;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getNumber() {
        return number;
    }

    public void setSignSet(Integer signSet) {
        this.signSet = signSet;
    }

    public Integer getSignSet() {
        return signSet;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("signConfigId", getSignConfigId())
                .append("signDay", getSignDay())
                .append("type", getType())
                .append("number", getNumber())
                .append("remark", getRemark())
                .append("signSet", getSignSet())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
