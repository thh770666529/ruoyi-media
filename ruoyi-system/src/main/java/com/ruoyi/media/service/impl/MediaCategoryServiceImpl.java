package com.ruoyi.media.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.media.mapper.MediaCategoryMapper;
import com.ruoyi.media.domain.MediaCategory;
import com.ruoyi.media.service.IMediaCategoryService;

/**
 * 电影分类Service业务层处理
 *
 * @author thh
 * @date 2021-09-11
 */
@Service
public class MediaCategoryServiceImpl implements IMediaCategoryService
{
    @Autowired
    private MediaCategoryMapper mediaCategoryMapper;

    /**
     * 查询电影分类
     *
     * @param categoryId 电影分类主键
     * @return 电影分类
     */
    @Override
    public MediaCategory selectMediaCategoryByCategoryId(Long categoryId)
    {
        return mediaCategoryMapper.selectMediaCategoryByCategoryId(categoryId);
    }

    /**
     * 查询电影分类列表
     *
     * @param mediaCategory 电影分类
     * @return 电影分类
     */
    @Override
    public List<MediaCategory> selectMediaCategoryList(MediaCategory mediaCategory)
    {
        return mediaCategoryMapper.selectMediaCategoryList(mediaCategory);
    }

    /**
     * 新增电影分类
     *
     * @param mediaCategory 电影分类
     * @return 结果
     */
    @Override
    public int insertMediaCategory(MediaCategory mediaCategory)
    {
        mediaCategory.setCreateTime(DateUtils.getNowDate());
        return mediaCategoryMapper.insert(mediaCategory);
    }

    /**
     * 修改电影分类
     *
     * @param mediaCategory 电影分类
     * @return 结果
     */
    @Override
    public int updateMediaCategory(MediaCategory mediaCategory)
    {
        mediaCategory.setUpdateTime(DateUtils.getNowDate());
        return mediaCategoryMapper.updateById(mediaCategory);
    }

    /**
     * 批量删除电影分类
     *
     * @param categoryIds 需要删除的电影分类主键
     * @return 结果
     */
    @Override
    public int deleteMediaCategoryByCategoryIds(Long[] categoryIds)
    {
        return mediaCategoryMapper.deleteMediaCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除电影分类信息
     *
     * @param categoryId 电影分类主键
     * @return 结果
     */
    @Override
    public int deleteMediaCategoryByCategoryId(Long categoryId)
    {
        return mediaCategoryMapper.deleteById(categoryId);
    }
}
