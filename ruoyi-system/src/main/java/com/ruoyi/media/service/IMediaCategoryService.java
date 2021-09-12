package com.ruoyi.media.service;

import java.util.List;
import com.ruoyi.media.domain.MediaCategory;

/**
 * 电影分类Service接口
 * 
 * @author thh
 * @date 2021-09-11
 */
public interface IMediaCategoryService 
{
    /**
     * 查询电影分类
     * 
     * @param categoryId 电影分类主键
     * @return 电影分类
     */
    public MediaCategory selectMediaCategoryByCategoryId(Long categoryId);

    /**
     * 查询电影分类列表
     * 
     * @param mediaCategory 电影分类
     * @return 电影分类集合
     */
    public List<MediaCategory> selectMediaCategoryList(MediaCategory mediaCategory);

    /**
     * 新增电影分类
     * 
     * @param mediaCategory 电影分类
     * @return 结果
     */
    public int insertMediaCategory(MediaCategory mediaCategory);

    /**
     * 修改电影分类
     * 
     * @param mediaCategory 电影分类
     * @return 结果
     */
    public int updateMediaCategory(MediaCategory mediaCategory);

    /**
     * 批量删除电影分类
     * 
     * @param categoryIds 需要删除的电影分类主键集合
     * @return 结果
     */
    public int deleteMediaCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 删除电影分类信息
     * 
     * @param categoryId 电影分类主键
     * @return 结果
     */
    public int deleteMediaCategoryByCategoryId(Long categoryId);
}
