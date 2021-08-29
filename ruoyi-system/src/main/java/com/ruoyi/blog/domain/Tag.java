package com.ruoyi.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章标签对象 blog_tag
 *
 * @author thh
 * @date 2021-08-28
 */
@Data
@TableName("blog_tag")
public class Tag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long tagId;

    /** 标签内容 */
    @Excel(name = "标签内容")
    private String content;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 标签点击数 */
    private Long clickCount;

    /** 排序字 */
    @Excel(name = "排序")
    private Long sort;

}
