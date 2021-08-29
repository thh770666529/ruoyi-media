package com.ruoyi.blog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.blog.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 * 博客分类Mapper接口
 *
 * @author thh
 * @date 2021-08-28
 */
@Repository
public interface SortMapper extends BaseMapper<Sort>
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
     * 批量删除博客分类
     *
     * @param sortIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSortBySortIds(Long[] sortIds);
}
