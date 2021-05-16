package com.ruoyi.media.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 演员对象 wm_actor
 *
 * @author thh
 * @date 2021-05-16
 */
@Data
public class Actor extends BaseEntity
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

}
