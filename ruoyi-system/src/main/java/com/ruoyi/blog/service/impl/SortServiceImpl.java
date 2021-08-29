package com.ruoyi.blog.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.blog.mapper.SortMapper;
import com.ruoyi.blog.domain.Sort;
import com.ruoyi.blog.service.ISortService;

/**
 * 博客分类Service业务层处理
 *
 * @author thh
 * @date 2021-08-28
 */
@Service
public class SortServiceImpl implements ISortService
{
    @Autowired
    private SortMapper sortMapper;

    /**
     * 查询博客分类
     *
     * @param sortId 博客分类主键
     * @return 博客分类
     */
    @Override
    public Sort selectSortBySortId(Long sortId)
    {
        return sortMapper.selectSortBySortId(sortId);
    }

    /**
     * 查询博客分类列表
     *
     * @param sort 博客分类
     * @return 博客分类
     */
    @Override
    public List<Sort> selectSortList(Sort sort)
    {
        return sortMapper.selectSortList(sort);
    }

    /**
     * 新增博客分类
     *
     * @param sort 博客分类
     * @return 结果
     */
    @Override
    public int insertSort(Sort sort)
    {
        sort.setCreateTime(DateUtils.getNowDate());
        return sortMapper.insert(sort);
    }

    /**
     * 修改博客分类
     *
     * @param sort 博客分类
     * @return 结果
     */
    @Override
    public int updateSort(Sort sort)
    {
        sort.setUpdateTime(DateUtils.getNowDate());
        return sortMapper.updateById(sort);
    }

    /**
     * 批量删除博客分类
     *
     * @param sortIds 需要删除的博客分类主键
     * @return 结果
     */
    @Override
    public int deleteSortBySortIds(Long[] sortIds)
    {
        return sortMapper.deleteSortBySortIds(sortIds);
    }

    /**
     * 删除博客分类信息
     *
     * @param sortId 博客分类主键
     * @return 结果
     */
    @Override
    public int deleteSortBySortId(Long sortId)
    {
        return sortMapper.deleteById(sortId);
    }
}
