package com.ruoyi.website.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 积分记录对象 website_integral_record
 *
 * @author thh
 * @date 2022-05-13
 */
public class IntegralRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long integralRecordId;

    /**
     * 积分类型
     */
    @Excel(name = "积分类型")
    private Integer integralType;

    /**
     * 积分名称
     */
    @Excel(name = "积分名称")
    private String integralName;

    /**
     * 积分值
     */
    @Excel(name = "积分值")
    private Integer value;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private String userId;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;

    /**
     * 删除标志
     */
    private String delFlag;

    /**
     * 旧积分
     */
    @Excel(name = "旧积分")
    private Long oldValue;


    @Excel(name = "积分Id")
    private String resourceId;


    public void setIntegralRecordId(Long integralRecordId) {
        this.integralRecordId = integralRecordId;
    }

    public Long getIntegralRecordId() {
        return integralRecordId;
    }

    public void setIntegralType(Integer integralType) {
        this.integralType = integralType;
    }

    public Integer getIntegralType() {
        return integralType;
    }

    public void setIntegralName(String integralName) {
        this.integralName = integralName;
    }

    public String getIntegralName() {
        return integralName;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public Long getOldValue() {
        return oldValue;
    }

    public void setOldValue(Long oldValue) {
        this.oldValue = oldValue;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("integralRecordId", getIntegralRecordId())
                .append("integralType", getIntegralType())
                .append("integralName", getIntegralName())
                .append("value", getValue())
                .append("remark", getRemark())
                .append("userId", getUserId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("oldValue", getOldValue())
                .append("resourceId", getResourceId())
                .toString();
    }
}
