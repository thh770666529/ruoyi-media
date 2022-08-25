package com.ruoyi.question.mapper;

import java.util.List;

import com.ruoyi.question.domain.QuestionCategory;

/**
 * 问答分类Mapper接口
 *
 * @author thh
 * @date 2021-09-12
 */
public interface QuestionCategoryMapper {
    /**
     * 查询问答分类
     *
     * @param categoryId 问答分类主键
     * @return 问答分类
     */
    public QuestionCategory selectQuestionCategoryByCategoryId(Integer categoryId);

    /**
     * 查询问答分类列表
     *
     * @param questionCategory 问答分类
     * @return 问答分类集合
     */
    public List<QuestionCategory> selectQuestionCategoryList(QuestionCategory questionCategory);

    /**
     * 新增问答分类
     *
     * @param questionCategory 问答分类
     * @return 结果
     */
    public int insertQuestionCategory(QuestionCategory questionCategory);

    /**
     * 修改问答分类
     *
     * @param questionCategory 问答分类
     * @return 结果
     */
    public int updateQuestionCategory(QuestionCategory questionCategory);

    /**
     * 删除问答分类
     *
     * @param categoryId 问答分类主键
     * @return 结果
     */
    public int deleteQuestionCategoryByCategoryId(Integer categoryId);

    /**
     * 批量删除问答分类
     *
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuestionCategoryByCategoryIds(Integer[] categoryIds);
}
