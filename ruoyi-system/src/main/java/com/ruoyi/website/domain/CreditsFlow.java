package com.ruoyi.website.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 积分流水对象 website_credits_flow
 * 
 * @author thh
 * @date 2021-12-11
 */
public class CreditsFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long creditsId;

    /** 积分编号 */
    @Excel(name = "积分编号")
    private String creditsCode;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 账户id */
    @Excel(name = "账户id")
    private String accountId;

    /** 账户积分 */
    @Excel(name = "账户积分")
    private Long account;

    /** 消耗前的账号积分 */
    @Excel(name = "消耗前的账号积分")
    private Long preAccount;

    /** 消耗积分 */
    @Excel(name = "消耗积分")
    private Long postAcount;

    /** 账号 */
    @Excel(name = "账号")
    private Integer status;

    /** 积分类型 */
    @Excel(name = "积分类型")
    private String creditsType;

    /** 删除标志 */
    private Integer delFlag;

    /** 描述 */
    @Excel(name = "描述")
    private String creditsDesc;

    public void setCreditsId(Long creditsId) 
    {
        this.creditsId = creditsId;
    }

    public Long getCreditsId() 
    {
        return creditsId;
    }
    public void setCreditsCode(String creditsCode) 
    {
        this.creditsCode = creditsCode;
    }

    public String getCreditsCode() 
    {
        return creditsCode;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setAccountId(String accountId) 
    {
        this.accountId = accountId;
    }

    public String getAccountId() 
    {
        return accountId;
    }
    public void setAccount(Long account) 
    {
        this.account = account;
    }

    public Long getAccount() 
    {
        return account;
    }
    public void setPreAccount(Long preAccount) 
    {
        this.preAccount = preAccount;
    }

    public Long getPreAccount() 
    {
        return preAccount;
    }
    public void setPostAcount(Long postAcount) 
    {
        this.postAcount = postAcount;
    }

    public Long getPostAcount() 
    {
        return postAcount;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setCreditsType(String creditsType) 
    {
        this.creditsType = creditsType;
    }

    public String getCreditsType() 
    {
        return creditsType;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }
    public void setCreditsDesc(String creditsDesc) 
    {
        this.creditsDesc = creditsDesc;
    }

    public String getCreditsDesc() 
    {
        return creditsDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("creditsId", getCreditsId())
            .append("creditsCode", getCreditsCode())
            .append("userId", getUserId())
            .append("accountId", getAccountId())
            .append("account", getAccount())
            .append("preAccount", getPreAccount())
            .append("postAcount", getPostAcount())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("creditsType", getCreditsType())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("creditsDesc", getCreditsDesc())
            .toString();
    }
}
