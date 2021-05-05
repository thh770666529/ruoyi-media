package com.ruoyi.website.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 站点友情链接对象 website_link
 * 
 * @author thh
 * @date 2021-05-05
 */
public class WebsiteLink extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long linkId;

    /** 状态(1有效, 0无效) */
    @Excel(name = "状态(1有效, 0无效)")
    private Integer status;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 名称 */
    @Excel(name = "名称")
    private String linkName;

    /** 链接 */
    @Excel(name = "链接")
    private String linkUrl;

    /** 跳转方式(_blank，_self) */
    @Excel(name = "跳转方式(_blank，_self)")
    private String linkTarget;

    public void setLinkId(Long linkId) 
    {
        this.linkId = linkId;
    }

    public Long getLinkId() 
    {
        return linkId;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setLinkName(String linkName) 
    {
        this.linkName = linkName;
    }

    public String getLinkName() 
    {
        return linkName;
    }
    public void setLinkUrl(String linkUrl) 
    {
        this.linkUrl = linkUrl;
    }

    public String getLinkUrl() 
    {
        return linkUrl;
    }
    public void setLinkTarget(String linkTarget) 
    {
        this.linkTarget = linkTarget;
    }

    public String getLinkTarget() 
    {
        return linkTarget;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("linkId", getLinkId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("sort", getSort())
            .append("linkName", getLinkName())
            .append("linkUrl", getLinkUrl())
            .append("linkTarget", getLinkTarget())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
