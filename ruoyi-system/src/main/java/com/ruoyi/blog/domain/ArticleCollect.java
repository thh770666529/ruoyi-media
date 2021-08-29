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
 * 文章收藏对象 blog_article_collect
 *
 * @author thh
 * @date 2021-08-28
 */
@Data
@TableName("blog_article_collect")
public class ArticleCollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long articleCollectId;

    /** 用户的id */
    @Excel(name = "用户的id")
    private String userId;

    /** 用户ip */
    @Excel(name = "用户ip")
    private String userIp;

    /** 博客的id */
    @Excel(name = "博客的id")
    private String artcileId;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

}
