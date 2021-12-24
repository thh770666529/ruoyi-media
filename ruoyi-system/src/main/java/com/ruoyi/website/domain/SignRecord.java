package com.ruoyi.website.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 签到日志对象 website_sign_record
 * 
 * @author thh
 * @date 2021-12-11
 */
@Data
public class SignRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long signRecordId;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private String userId;

    /**
     * 奖励
     */
    @Excel(name = "奖励")
    private String signReward;

    /**
     * 签到日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signDate;

    /**
     * 签到类型1=签到2=补签
     */
    @Excel(name = "签到类型1=签到2=补签")
    private Integer signType;
}