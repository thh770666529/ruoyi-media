package com.ruoyi.blog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.blog.domain.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 博客文章Mapper接口
 *
 * @author thh
 * @date 2021-08-28
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article>
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
     * 批量删除博客文章
     *
     * @param articleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArticleByArticleIds(Long[] articleIds);

    /**
     * 搜索最热文章列表
     * @param status
     * @param publishStatus
     * @param top
     * @return
     */
    List<Article> selectHotArticleList(@Param("status") int status,@Param("publishStatus") int publishStatus,@Param("top") int top);

    /**
     * 通过门户获取文章列表
     * @param article
     * @return
     */
    List<Article> selectSearchArticleList(Article article);
}
