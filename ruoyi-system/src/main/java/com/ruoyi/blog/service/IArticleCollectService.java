package com.ruoyi.blog.service;

import java.util.List;
import com.ruoyi.blog.domain.ArticleCollect;

/**
 * 文章收藏Service接口
 * 
 * @author thh
 * @date 2021-08-28
 */
public interface IArticleCollectService 
{
    /**
     * 查询文章收藏
     * 
     * @param articleCollectId 文章收藏主键
     * @return 文章收藏
     */
    public ArticleCollect selectArticleCollectByArticleCollectId(Long articleCollectId);

    /**
     * 查询文章收藏列表
     * 
     * @param articleCollect 文章收藏
     * @return 文章收藏集合
     */
    public List<ArticleCollect> selectArticleCollectList(ArticleCollect articleCollect);

    /**
     * 新增文章收藏
     * 
     * @param articleCollect 文章收藏
     * @return 结果
     */
    public int insertArticleCollect(ArticleCollect articleCollect);

    /**
     * 修改文章收藏
     * 
     * @param articleCollect 文章收藏
     * @return 结果
     */
    public int updateArticleCollect(ArticleCollect articleCollect);

    /**
     * 批量删除文章收藏
     * 
     * @param articleCollectIds 需要删除的文章收藏主键集合
     * @return 结果
     */
    public int deleteArticleCollectByArticleCollectIds(Long[] articleCollectIds);

    /**
     * 删除文章收藏信息
     * 
     * @param articleCollectId 文章收藏主键
     * @return 结果
     */
    public int deleteArticleCollectByArticleCollectId(Long articleCollectId);
}
