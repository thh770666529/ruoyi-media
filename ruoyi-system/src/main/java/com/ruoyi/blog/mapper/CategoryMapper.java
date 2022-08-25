package com.ruoyi.blog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.blog.domain.Category;
import org.springframework.stereotype.Repository;

/**
 * 博客分类Mapper接口
 *
 * @author thh
 * @date 2021-08-28
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 查询博客分类
     *
     * @param categoryId 博客分类主键
     * @return 博客分类
     */
    public Category selectCategoryByCategoryId(Long categoryId);

    /**
     * 查询博客分类列表
     *
     * @param category 博客分类
     * @return 博客分类集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 批量删除博客分类
     *
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByCategoryIds(Long[] categoryIds);
}
