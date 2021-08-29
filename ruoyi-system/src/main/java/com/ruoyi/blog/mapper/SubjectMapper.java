package com.ruoyi.blog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.blog.domain.Subject;
import com.ruoyi.blog.domain.ArticleSubject;
import org.springframework.stereotype.Repository;

/**
 * 文章专题Mapper接口
 *
 * @author thh
 * @date 2021-08-28
 */
@Repository
public interface SubjectMapper extends BaseMapper<Subject>
{
    /**
     * 查询文章专题
     *
     * @param subjectId 文章专题主键
     * @return 文章专题
     */
    public Subject selectSubjectBySubjectId(Long subjectId);

    /**
     * 查询文章专题列表
     *
     * @param subject 文章专题
     * @return 文章专题集合
     */
    public List<Subject> selectSubjectList(Subject subject);


    /**
     * 批量删除文章专题
     *
     * @param subjectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubjectBySubjectIds(Long[] subjectIds);

    /**
     * 批量删除文章专题Item
     *
     * @param subjectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArticleSubjectBySubjectIds(Long[] subjectIds);

    /**
     * 批量新增文章专题Item
     *
     * @param articleSubjectList 文章专题Item列表
     * @return 结果
     */
    public int batchArticleSubject(List<ArticleSubject> articleSubjectList);


    /**
     * 通过文章专题主键删除文章专题Item信息
     *
     * @param subjectId 文章专题ID
     * @return 结果
     */
    public int deleteArticleSubjectBySubjectId(Long subjectId);
}
