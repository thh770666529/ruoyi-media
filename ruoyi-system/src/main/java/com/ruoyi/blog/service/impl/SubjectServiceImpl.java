package com.ruoyi.blog.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.blog.domain.ArticleSubject;
import com.ruoyi.blog.mapper.SubjectMapper;
import com.ruoyi.blog.domain.Subject;
import com.ruoyi.blog.service.ISubjectService;

/**
 * 文章专题Service业务层处理
 *
 * @author thh
 * @date 2021-08-28
 */
@Service
public class SubjectServiceImpl implements ISubjectService
{
    @Autowired
    private SubjectMapper subjectMapper;

    /**
     * 查询文章专题
     *
     * @param subjectId 文章专题主键
     * @return 文章专题
     */
    @Override
    public Subject selectSubjectBySubjectId(Long subjectId)
    {
        return subjectMapper.selectSubjectBySubjectId(subjectId);
    }

    /**
     * 查询文章专题列表
     *
     * @param subject 文章专题
     * @return 文章专题
     */
    @Override
    public List<Subject> selectSubjectList(Subject subject)
    {
        return subjectMapper.selectSubjectList(subject);
    }

    /**
     * 新增文章专题
     *
     * @param subject 文章专题
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSubject(Subject subject)
    {
        subject.setCreateTime(DateUtils.getNowDate());
        int rows = subjectMapper.insert(subject);
        insertArticleSubject(subject);
        return rows;
    }

    /**
     * 修改文章专题
     *
     * @param subject 文章专题
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSubject(Subject subject)
    {
        subject.setUpdateTime(DateUtils.getNowDate());
        subjectMapper.deleteArticleSubjectBySubjectId(subject.getSubjectId());
        insertArticleSubject(subject);
        return subjectMapper.updateById(subject);
    }

    /**
     * 批量删除文章专题
     *
     * @param subjectIds 需要删除的文章专题主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSubjectBySubjectIds(Long[] subjectIds)
    {
        subjectMapper.deleteArticleSubjectBySubjectIds(subjectIds);
        return subjectMapper.deleteSubjectBySubjectIds(subjectIds);
    }

    /**
     * 删除文章专题信息
     *
     * @param subjectId 文章专题主键
     * @return 结果
     */
    @Override
    public int deleteSubjectBySubjectId(Long subjectId)
    {
        subjectMapper.deleteArticleSubjectBySubjectId(subjectId);
        return subjectMapper.deleteById(subjectId);
    }

    /**
     * 新增文章专题Item信息
     *
     * @param subject 文章专题对象
     */
    public void insertArticleSubject(Subject subject)
    {
        List<ArticleSubject> articleSubjectList = subject.getArticleSubjectList();
        Long subjectId = subject.getSubjectId();
        if (StringUtils.isNotNull(articleSubjectList))
        {
            List<ArticleSubject> list = new ArrayList<ArticleSubject>();
            for (ArticleSubject articleSubject : articleSubjectList)
            {
                articleSubject.setSubjectId(subjectId + "");
                list.add(articleSubject);
            }
            if (list.size() > 0)
            {
                subjectMapper.batchArticleSubject(list);
            }
        }
    }
}
