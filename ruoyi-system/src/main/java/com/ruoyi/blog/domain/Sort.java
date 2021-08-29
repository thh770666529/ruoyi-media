package com.ruoyi.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客分类对象 blog_sort
 *
 * @author thh
 * @date 2021-08-28
 */
@Data
@TableName("blog_sort")
public class Sort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    @TableId(type = IdType.AUTO)
    private Long sortId;

    /** 分类内容 */
    @Excel(name = "分类内容")
    private String name;

    /** 分类简介 */
    @Excel(name = "分类简介")
    private String content;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 排序字段，越大越靠前 */
    @Excel(name = "排序字段，越大越靠前")
    private Long sort;

    /** 点击数 */
    @Excel(name = "点击数")
    private Long clickCount;

}
