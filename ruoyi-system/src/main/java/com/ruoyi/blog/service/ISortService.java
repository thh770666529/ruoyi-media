package com.ruoyi.blog.service;

import java.util.List;
import com.ruoyi.blog.domain.Sort;

/**
 * 博客分类Service接口
 * 
 * @author thh
 * @date 2021-08-28
 */
public interface ISortService 
{
    /**
     * 查询博客分类
     * 
     * @param sortId 博客分类主键
     * @return 博客分类
     */
    public Sort selectSortBySortId(Long sortId);

    /**
     * 查询博客分类列表
     * 
     * @param sort 博客分类
     * @return 博客分类集合
     */
    public List<Sort> selectSortList(Sort sort);

    /**
     * 新增博客分类
     * 
     * @param sort 博客分类
     * @return 结果
     */
    public int insertSort(Sort sort);

    /**
     * 修改博客分类
     * 
     * @param sort 博客分类
     * @return 结果
     */
    public int updateSort(Sort sort);

    /**
     * 批量删除博客分类
     * 
     * @param sortIds 需要删除的博客分类主键集合
     * @return 结果
     */
    public int deleteSortBySortIds(Long[] sortIds);

    /**
     * 删除博客分类信息
     * 
     * @param sortId 博客分类主键
     * @return 结果
     */
    public int deleteSortBySortId(Long sortId);
}
