package com.ruoyi.website.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 首页banner对象 website_banner
 * 
 * @author thh
 * @date 2021-05-05
 */
public class WebsiteBanner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long bannerId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imageUrl;

    /** 链接地址 */
    @Excel(name = "链接地址")
    private String linkUrl;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 逻辑删除 1（true）已删除， 0（false）未删除 */
    private String delFlag;

    public void setBannerId(Long bannerId) 
    {
        this.bannerId = bannerId;
    }

    public Long getBannerId() 
    {
        return bannerId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }
    public void setLinkUrl(String linkUrl) 
    {
        this.linkUrl = linkUrl;
    }

    public String getLinkUrl() 
    {
        return linkUrl;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bannerId", getBannerId())
            .append("title", getTitle())
            .append("imageUrl", getImageUrl())
            .append("linkUrl", getLinkUrl())
            .append("sort", getSort())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
