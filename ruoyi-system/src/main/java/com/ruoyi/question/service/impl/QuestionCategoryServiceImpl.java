package com.ruoyi.question.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.question.mapper.QuestionCategoryMapper;
import com.ruoyi.question.domain.QuestionCategory;
import com.ruoyi.question.service.IQuestionCategoryService;

/**
 * 问答分类Service业务层处理
 * 
 * @author thh
 * @date 2021-09-12
 */
@Service
public class QuestionCategoryServiceImpl implements IQuestionCategoryService 
{
    @Autowired
    private QuestionCategoryMapper questionCategoryMapper;

    /**
     * 查询问答分类
     * 
     * @param categoryId 问答分类主键
     * @return 问答分类
     */
    @Override
    public QuestionCategory selectQuestionCategoryByCategoryId(Integer categoryId)
    {
        return questionCategoryMapper.selectQuestionCategoryByCategoryId(categoryId);
    }

    /**
     * 查询问答分类列表
     * 
     * @param questionCategory 问答分类
     * @return 问答分类
     */
    @Override
    public List<QuestionCategory> selectQuestionCategoryList(QuestionCategory questionCategory)
    {
        return questionCategoryMapper.selectQuestionCategoryList(questionCategory);
    }

    /**
     * 新增问答分类
     * 
     * @param questionCategory 问答分类
     * @return 结果
     */
    @Override
    public int insertQuestionCategory(QuestionCategory questionCategory)
    {
        questionCategory.setCreateTime(DateUtils.getNowDate());
        return questionCategoryMapper.insertQuestionCategory(questionCategory);
    }

    /**
     * 修改问答分类
     * 
     * @param questionCategory 问答分类
     * @return 结果
     */
    @Override
    public int updateQuestionCategory(QuestionCategory questionCategory)
    {
        questionCategory.setUpdateTime(DateUtils.getNowDate());
        return questionCategoryMapper.updateQuestionCategory(questionCategory);
    }

    /**
     * 批量删除问答分类
     * 
     * @param categoryIds 需要删除的问答分类主键
     * @return 结果
     */
    @Override
    public int deleteQuestionCategoryByCategoryIds(Integer[] categoryIds)
    {
        return questionCategoryMapper.deleteQuestionCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除问答分类信息
     * 
     * @param categoryId 问答分类主键
     * @return 结果
     */
    @Override
    public int deleteQuestionCategoryByCategoryId(Integer categoryId)
    {
        return questionCategoryMapper.deleteQuestionCategoryByCategoryId(categoryId);
    }
}
