package com.ruoyi.blog.service;

import java.util.List;
import com.ruoyi.blog.domain.Subject;

/**
 * 文章专题Service接口
 * 
 * @author thh
 * @date 2021-08-28
 */
public interface ISubjectService 
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
     * 新增文章专题
     * 
     * @param subject 文章专题
     * @return 结果
     */
    public int insertSubject(Subject subject);

    /**
     * 修改文章专题
     * 
     * @param subject 文章专题
     * @return 结果
     */
    public int updateSubject(Subject subject);

    /**
     * 批量删除文章专题
     * 
     * @param subjectIds 需要删除的文章专题主键集合
     * @return 结果
     */
    public int deleteSubjectBySubjectIds(Long[] subjectIds);

    /**
     * 删除文章专题信息
     * 
     * @param subjectId 文章专题主键
     * @return 结果
     */
    public int deleteSubjectBySubjectId(Long subjectId);
}
