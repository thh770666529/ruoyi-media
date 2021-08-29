package com.ruoyi.blog.domain;

import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章专题对象 blog_subject
 *
 * @author thh
 * @date 2021-08-28
 */
@Data
@TableName("blog_subject")
public class Subject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long subjectId;

    /** 专题名称 */
    @Excel(name = "专题名称")
    private String name;

    /** 简介 */
    @Excel(name = "简介")
    private String summary;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String images;

    /** 图标 */
    private String icon;

    /** 专题点击数 */
    private Long clickCount;

    /** 专题收藏数 */
    private Long collectCount;

    /** 排序字段 */
    @Excel(name = "排序字段")
    private Long sort;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 文章专题Item信息 */
    @TableField(exist = false)
    private List<ArticleSubject> articleSubjectList;

}
