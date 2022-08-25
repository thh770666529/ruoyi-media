package com.ruoyi.blog.domain.vo;

import com.ruoyi.blog.domain.Article;
import com.ruoyi.blog.domain.Category;
import com.ruoyi.blog.domain.Tag;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * 博客文章对象 blog_article
 *
 * @author thh
 * @date 2021-08-28
 */
@Data
public class ArticleVO extends Article {
    /**
     * 版权
     */
    private String copyright;

    /**
     * 标签列表
     */
    private List<Tag> tagList;

    /**
     * 分类
     */
    private Category categoryData;

    /**
     * 作者
     */
    private SysUser authorUser;

    /**
     * 用户是否已经点赞
     * 24小时以内无法点赞
     */
    private Boolean thumbFlag;
}
