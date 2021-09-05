package com.ruoyi.blog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.blog.domain.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 文章标签Mapper接口
 *
 * @author thh
 * @date 2021-08-28
 */
@Repository
public interface TagMapper extends BaseMapper<Tag>
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
     * 批量删除文章标签
     *
     * @param tagIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTagByTagIds(Long[] tagIds);

    /**
     * 获取最热标签
     * @param status
     * @param top
     * @return
     */
    List<Tag> selectHotTagList(@Param("status") int status, @Param("top") int top);
}
