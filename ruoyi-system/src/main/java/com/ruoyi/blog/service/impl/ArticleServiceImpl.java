package com.ruoyi.blog.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.constant.BaseRedisKeyConstants;
import com.ruoyi.common.constant.BlogConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.blog.mapper.ArticleMapper;
import com.ruoyi.blog.domain.Article;
import com.ruoyi.blog.service.IArticleService;

/**
 * 博客文章Service业务层处理
 *
 * @author thh
 * @date 2021-08-28
 */
@Service
public class ArticleServiceImpl implements IArticleService
{
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询博客文章
     *
     * @param articleId 博客文章主键
     * @return 博客文章
     */
    @Override
    public Article selectArticleByArticleId(Long articleId)
    {
        return articleMapper.selectArticleByArticleId(articleId);
    }

    /**
     * 查询博客文章列表
     *
     * @param article 博客文章
     * @return 博客文章
     */
    @Override
    public List<Article> selectArticleList(Article article)
    {
        return articleMapper.selectArticleList(article);
    }

    @Override
    public List<Article> selectWebArticleList(Article article) {
        article.setIsPublish("1");
        article.setStatus(1);
        return articleMapper.selectArticleList(article);
    }

    /**
     * 新增博客文章
     *
     * @param article 博客文章
     * @return 结果
     */
    @Override
    public int insertArticle(Article article)
    {
        article.setCreateTime(DateUtils.getNowDate());
        return articleMapper.insert(article);
    }

    /**
     * 修改博客文章
     *
     * @param article 博客文章
     * @return 结果
     */
    @Override
    public int updateArticle(Article article)
    {
        article.setUpdateTime(DateUtils.getNowDate());
        return articleMapper.updateById(article);
    }

    /**
     * 批量删除博客文章
     *
     * @param articleIds 需要删除的博客文章主键
     * @return 结果
     */
    @Override
    public int deleteArticleByArticleIds(Long[] articleIds)
    {
        return articleMapper.deleteArticleByArticleIds(articleIds);
    }

    /**
     * 删除博客文章信息
     *
     * @param articleId 博客文章主键
     * @return 结果
     */
    @Override
    public int deleteArticleByArticleId(Long articleId)
    {
        return articleMapper.deleteById(articleId);
    }

    @Override
    public List<Article> selectHotArticleList(int top) {
        List<Article> articleList = redisCache.getCacheObject(BaseRedisKeyConstants.HOT_BLOG_ARTICLE);
        if (CollectionUtil.isEmpty(articleList)) {
            articleList = articleMapper.selectHotArticleList(1, 1, top);
            if (CollectionUtil.isNotEmpty(articleList)){
                redisCache.setCacheObject(BaseRedisKeyConstants.HOT_BLOG_ARTICLE, articleList, BlogConstants.BLOG_ARTICLE_EXPIRATION, TimeUnit.DAYS);
            }
        }
        return articleList;
    }

    @Override
    public List<Article> selectNewArticleList() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("is_publish", "1");
        queryWrapper.orderByDesc("create_time");
        //因为首页并不需要显示内容，所以需要排除掉内容字段
        queryWrapper.select(Article.class, i -> !i.getProperty().equals("content"));
        return articleMapper.selectList(queryWrapper);
    }

    @Override
    public List<Article> selectSearchArticleList(Article article) {
        article.setIsPublish("1");
        article.setStatus(1);
        return articleMapper.selectSearchArticleList(article);
    }

    @Override
    public Article selectWebArticleByArticleId(Long articleId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        queryWrapper.eq("status", 1);
        queryWrapper.eq("is_publish", "1");
        return  articleMapper.selectOne(queryWrapper);
    }

    @Override
    public void praiseArticleById(Long articleId) {
        Long userId = SecurityUtils.getUserId();
        Integer redisJson = redisCache.getCacheObject(BaseRedisKeyConstants.CIICK_BLOG_LOVE + ":" + articleId + "#" + userId);
        if (redisJson != null){
            throw new ServiceException("24小时内无法重复点赞同一篇文章");
        } else {
            Article article = articleMapper.selectById(articleId);
            Long praiseClickCount = article.getPraiseClickCount();
            if (praiseClickCount == null){
                praiseClickCount = 0L;
            }
            article.setPraiseClickCount(praiseClickCount + 1);
            articleMapper.insert(article);
            redisCache.setCacheObject(BaseRedisKeyConstants.CIICK_BLOG_LOVE + ":" + articleId + "#" + userId, 1);
        }
    }
}
