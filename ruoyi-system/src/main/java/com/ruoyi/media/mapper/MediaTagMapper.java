package com.ruoyi.media.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.media.domain.MediaTag;

/**
 * 电影标签Mapper接口
 *
 * @author thh
 * @date 2021-09-11
 */
public interface MediaTagMapper extends BaseMapper<MediaTag>
{
    /**
     * 查询电影标签
     *
     * @param tagId 电影标签主键
     * @return 电影标签
     */
    public MediaTag selectMediaTagByTagId(Long tagId);

    /**
     * 查询电影标签列表
     *
     * @param mediaTag 电影标签
     * @return 电影标签集合
     */
    public List<MediaTag> selectMediaTagList(MediaTag mediaTag);

    /**
     * 批量删除电影标签
     *
     * @param tagIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMediaTagByTagIds(Long[] tagIds);
}
