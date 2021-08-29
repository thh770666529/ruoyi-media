package com.ruoyi.blog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.blog.domain.ArticleCollect;
import org.springframework.stereotype.Repository;

/**
 * 文章收藏Mapper接口
 *
 * @author thh
 * @date 2021-08-28
 */
@Repository
public interface ArticleCollectMapper extends BaseMapper<ArticleCollect>
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
     * 批量删除文章收藏
     *
     * @param articleCollectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArticleCollectByArticleCollectIds(Long[] articleCollectIds);
}
