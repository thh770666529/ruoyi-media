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
 * 文章专题Item对象 blog_article_subject
 *
 * @author thh
 * @date 2021-08-28
 */
@Data
@TableName("blog_article_subject")
public class ArticleSubject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long articleSujectId;

    /** 专题id */
    private String subjectId;

    /** 博客id */
    private String articleId;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 排序字段 */
    @Excel(name = "排序字段")
    private Long sort;

}
