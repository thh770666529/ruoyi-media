package com.ruoyi.blog.service;

import java.util.List;

import com.ruoyi.blog.domain.Article;
import com.ruoyi.blog.domain.Tag;

/**
 * 文章标签Service接口
 *
 * @author thh
 * @date 2021-08-28
 */
public interface ITagService
{
    /**
     * 查询文章标签
     *
     * @param tagId 文章标签主键
     * @return 文章标签
     */
    public Tag selectTagByTagId(Long tagId);

    /**
     * 查询文章标签列表
     *
     * @param tag 文章标签
     * @return 文章标签集合
     */
    public List<Tag> selectTagList(Tag tag);

    /**
     * 新增文章标签
     *
     * @param tag 文章标签
     * @return 结果
     */
    public int insertTag(Tag tag);

    /**
     * 修改文章标签
     *
     * @param tag 文章标签
     * @return 结果
     */
    public int updateTag(Tag tag);

    /**
     * 批量删除文章标签
     *
     * @param tagIds 需要删除的文章标签主键集合
     * @return 结果
     */
    public int deleteTagByTagIds(Long[] tagIds);

    /**
     * 删除文章标签信息
     *
     * @param tagId 文章标签主键
     * @return 结果
     */
    public int deleteTagByTagId(Long tagId);

    /**
     * 获取热门标签
     * @param top
     * @return
     */
    List<Tag> selectHotTagList(int top);

    /**
     * 通过标签数组获取标签列表
     * @param tagIdList
     * @return
     */
    List<Tag> selectTagListByIds(List<String> tagIdList);

}
