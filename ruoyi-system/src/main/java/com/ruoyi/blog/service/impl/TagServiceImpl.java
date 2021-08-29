package com.ruoyi.blog.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.blog.mapper.TagMapper;
import com.ruoyi.blog.domain.Tag;
import com.ruoyi.blog.service.ITagService;

/**
 * 文章标签Service业务层处理
 *
 * @author thh
 * @date 2021-08-28
 */
@Service
public class TagServiceImpl implements ITagService
{
    @Autowired
    private TagMapper tagMapper;

    /**
     * 查询文章标签
     *
     * @param tagId 文章标签主键
     * @return 文章标签
     */
    @Override
    public Tag selectTagByTagId(Long tagId)
    {
        return tagMapper.selectTagByTagId(tagId);
    }

    /**
     * 查询文章标签列表
     *
     * @param tag 文章标签
     * @return 文章标签
     */
    @Override
    public List<Tag> selectTagList(Tag tag)
    {
        return tagMapper.selectTagList(tag);
    }

    /**
     * 新增文章标签
     *
     * @param tag 文章标签
     * @return 结果
     */
    @Override
    public int insertTag(Tag tag)
    {
        tag.setCreateTime(DateUtils.getNowDate());
        return tagMapper.insert(tag);
    }

    /**
     * 修改文章标签
     *
     * @param tag 文章标签
     * @return 结果
     */
    @Override
    public int updateTag(Tag tag)
    {
        tag.setUpdateTime(DateUtils.getNowDate());
        return tagMapper.updateById(tag);
    }

    /**
     * 批量删除文章标签
     *
     * @param tagIds 需要删除的文章标签主键
     * @return 结果
     */
    @Override
    public int deleteTagByTagIds(Long[] tagIds)
    {
        return tagMapper.deleteTagByTagIds(tagIds);
    }

    /**
     * 删除文章标签信息
     *
     * @param tagId 文章标签主键
     * @return 结果
     */
    @Override
    public int deleteTagByTagId(Long tagId)
    {
        return tagMapper.deleteById(tagId);
    }
}
