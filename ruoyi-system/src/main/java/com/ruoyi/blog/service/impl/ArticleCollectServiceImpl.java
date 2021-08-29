package com.ruoyi.blog.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.blog.mapper.ArticleCollectMapper;
import com.ruoyi.blog.domain.ArticleCollect;
import com.ruoyi.blog.service.IArticleCollectService;

/**
 * 文章收藏Service业务层处理
 *
 * @author thh
 * @date 2021-08-28
 */
@Service
public class ArticleCollectServiceImpl implements IArticleCollectService
{
    @Autowired
    private ArticleCollectMapper articleCollectMapper;

    /**
     * 查询文章收藏
     *
     * @param articleCollectId 文章收藏主键
     * @return 文章收藏
     */
    @Override
    public ArticleCollect selectArticleCollectByArticleCollectId(Long articleCollectId)
    {
        return articleCollectMapper.selectArticleCollectByArticleCollectId(articleCollectId);
    }

    /**
     * 查询文章收藏列表
     *
     * @param articleCollect 文章收藏
     * @return 文章收藏
     */
    @Override
    public List<ArticleCollect> selectArticleCollectList(ArticleCollect articleCollect)
    {
        return articleCollectMapper.selectArticleCollectList(articleCollect);
    }

    /**
     * 新增文章收藏
     *
     * @param articleCollect 文章收藏
     * @return 结果
     */
    @Override
    public int insertArticleCollect(ArticleCollect articleCollect)
    {
        articleCollect.setCreateTime(DateUtils.getNowDate());
        return articleCollectMapper.insert(articleCollect);
    }

    /**
     * 修改文章收藏
     *
     * @param articleCollect 文章收藏
     * @return 结果
     */
    @Override
    public int updateArticleCollect(ArticleCollect articleCollect)
    {
        articleCollect.setUpdateTime(DateUtils.getNowDate());
        return articleCollectMapper.updateById(articleCollect);
    }

    /**
     * 批量删除文章收藏
     *
     * @param articleCollectIds 需要删除的文章收藏主键
     * @return 结果
     */
    @Override
    public int deleteArticleCollectByArticleCollectIds(Long[] articleCollectIds)
    {
        return articleCollectMapper.deleteArticleCollectByArticleCollectIds(articleCollectIds);
    }

    /**
     * 删除文章收藏信息
     *
     * @param articleCollectId 文章收藏主键
     * @return 结果
     */
    @Override
    public int deleteArticleCollectByArticleCollectId(Long articleCollectId)
    {
        return articleCollectMapper.deleteById(articleCollectId);
    }
}
