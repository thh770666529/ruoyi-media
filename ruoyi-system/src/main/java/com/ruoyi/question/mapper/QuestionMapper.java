package com.ruoyi.question.mapper;

import java.util.List;

import com.ruoyi.question.domain.Question;

/**
 * 问答Mapper接口
 *
 * @author thh
 * @date 2021-09-12
 */
public interface QuestionMapper {
    /**
     * 查询问答
     *
     * @param questionId 问答主键
     * @return 问答
     */
    public Question selectQuestionByQuestionId(Long questionId);

    /**
     * 查询问答列表
     *
     * @param question 问答
     * @return 问答集合
     */
    public List<Question> selectQuestionList(Question question);

    /**
     * 新增问答
     *
     * @param question 问答
     * @return 结果
     */
    public int insertQuestion(Question question);

    /**
     * 修改问答
     *
     * @param question 问答
     * @return 结果
     */
    public int updateQuestion(Question question);

    /**
     * 删除问答
     *
     * @param questionId 问答主键
     * @return 结果
     */
    public int deleteQuestionByQuestionId(Long questionId);

    /**
     * 批量删除问答
     *
     * @param questionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuestionByQuestionIds(Long[] questionIds);
}
