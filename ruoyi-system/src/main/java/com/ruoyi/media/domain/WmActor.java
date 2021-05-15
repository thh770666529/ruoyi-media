package com.ruoyi.media.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 演员对象 wm_actor
 *
 * @author thh
 * @date 2021-05-15
 */
public class WmActor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long actorId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 简述 */
    @Excel(name = "简述")
    private String description;

    /** 奖项 */
    @Excel(name = "奖项")
    private String awards;

    /** 标签  */
    @Excel(name = "标签 ")
    private String label;

    public void setActorId(Long actorId)
    {
        this.actorId = actorId;
    }

    public Long getActorId()
    {
        return actorId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setAwards(String awards)
    {
        this.awards = awards;
    }

    public String getAwards()
    {
        return awards;
    }
    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("actorId", getActorId())
                .append("name", getName())
                .append("avatar", getAvatar())
                .append("description", getDescription())
                .append("awards", getAwards())
                .append("label", getLabel())
                .toString();
    }
}
