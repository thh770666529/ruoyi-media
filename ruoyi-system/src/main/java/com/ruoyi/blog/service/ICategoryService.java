package com.ruoyi.blog.service;

import java.util.List;
import com.ruoyi.blog.domain.Category;

/**
 * 博客分类Service接口
 *
 * @author thh
 * @date 2021-08-28
 */
public interface ICategoryService
{
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
     * 新增博客分类
     *
     * @param category 博客分类
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改博客分类
     *
     * @param category 博客分类
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除博客分类
     *
     * @param categoryIds 需要删除的博客分类主键集合
     * @return 结果
     */
    public int deleteCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 删除博客分类信息
     *
     * @param categoryId 博客分类主键
     * @return 结果
     */
    public int deleteCategoryByCategoryId(Long categoryId);
}
