package com.ruoyi.media.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.media.domain.MediaCategory;

/**
 * 电影分类Mapper接口
 *
 * @author thh
 * @date 2021-09-11
 */
public interface MediaCategoryMapper  extends BaseMapper<MediaCategory>
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
     * 批量删除电影分类
     *
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMediaCategoryByCategoryIds(Long[] categoryIds);
}
