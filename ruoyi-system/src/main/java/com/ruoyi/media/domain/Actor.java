package com.ruoyi.media.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("wm_actor")
public class Actor extends BaseEntity
{

    /** 主键 */
    @TableId(type = IdType.AUTO)
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
