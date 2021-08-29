package com.ruoyi.blog.service;

import java.util.List;
import com.ruoyi.blog.domain.Article;

/**
 * 博客文章Service接口
 * 
 * @author thh
 * @date 2021-08-28
 */
public interface IArticleService 
{
    /**
     * 查询博客文章
     * 
     * @param articleId 博客文章主键
     * @return 博客文章
     */
    public Article selectArticleByArticleId(Long articleId);

    /**
     * 查询博客文章列表
     * 
     * @param article 博客文章
     * @return 博客文章集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 新增博客文章
     * 
     * @param article 博客文章
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改博客文章
     * 
     * @param article 博客文章
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 批量删除博客文章
     * 
     * @param articleIds 需要删除的博客文章主键集合
     * @return 结果
     */
    public int deleteArticleByArticleIds(Long[] articleIds);

    /**
     * 删除博客文章信息
     * 
     * @param articleId 博客文章主键
     * @return 结果
     */
    public int deleteArticleByArticleId(Long articleId);
}
