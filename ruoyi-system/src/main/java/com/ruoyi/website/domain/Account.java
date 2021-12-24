package com.ruoyi.website.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户账户对象 website_account
 *
 * @author thh
 * @date 2021-12-11
 */
@Data
public class Account extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long accountId;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 账户积分 */
    @Excel(name = "账户积分")
    private Long accountAmount;

    /** 账户描述 */
    @Excel(name = "账户描述")
    private String accountDesc;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 删除标志 */
    private Integer delFlag;

    /** 累计签到天数 */
    @Excel(name = "累计签到天数")
    private Integer seriesDays;

    /** 连续签到天数 */
    @Excel(name = "连续签到天数")
    private Integer continuityDays;

    /** 最后签到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后签到时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastSignTime;

    /** 可补签次数 */
    @Excel(name = "可补签次数")
    private Integer signNums;

    /** 最后签到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "签到数据最后更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date signDataUpdateTime;
}
