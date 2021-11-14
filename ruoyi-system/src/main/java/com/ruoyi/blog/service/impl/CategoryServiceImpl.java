package com.ruoyi.blog.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.blog.mapper.CategoryMapper;
import com.ruoyi.blog.domain.Category;
import com.ruoyi.blog.service.ICategoryService;

/**
 * 博客分类Service业务层处理
 *
 * @author thh
 * @date 2021-08-28
 */
@Service
public class CategoryServiceImpl  extends ServiceImpl<CategoryMapper,Category> implements ICategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询博客分类
     *
     * @param categoryId 博客分类主键
     * @return 博客分类
     */
    @Override
    public Category selectCategoryByCategoryId(Long categoryId)
    {
        return categoryMapper.selectCategoryByCategoryId(categoryId);
    }

    /**
     * 查询博客分类列表
     *
     * @param category 博客分类
     * @return 博客分类
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增博客分类
     *
     * @param category 博客分类
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        category.setCreateTime(DateUtils.getNowDate());
        return categoryMapper.insert(category);
    }

    /**
     * 修改博客分类
     *
     * @param category 博客分类
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.updateById(category);
    }

    /**
     * 批量删除博客分类
     *
     * @param categoryIds 需要删除的博客分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCategoryIds(Long[] categoryIds)
    {
        return categoryMapper.deleteCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除博客分类信息
     *
     * @param categoryId 博客分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCategoryId(Long categoryId)
    {
        return categoryMapper.deleteById(categoryId);
    }

    @Override
    public Category getTopOne() {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("status", "1");
        categoryQueryWrapper.last("limit 1");
        categoryQueryWrapper.orderByDesc("sort");
        return categoryMapper.selectOne(categoryQueryWrapper);
    }
}
