package com.ruoyi.media.service;

import java.util.List;

import com.ruoyi.media.domain.MediaTag;

/**
 * 电影标签Service接口
 *
 * @author thh
 * @date 2021-09-11
 */
public interface IMediaTagService {
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
     * 新增电影标签
     *
     * @param mediaTag 电影标签
     * @return 结果
     */
    public int insertMediaTag(MediaTag mediaTag);

    /**
     * 修改电影标签
     *
     * @param mediaTag 电影标签
     * @return 结果
     */
    public int updateMediaTag(MediaTag mediaTag);

    /**
     * 批量删除电影标签
     *
     * @param tagIds 需要删除的电影标签主键集合
     * @return 结果
     */
    public int deleteMediaTagByTagIds(Long[] tagIds);

    /**
     * 删除电影标签信息
     *
     * @param tagId 电影标签主键
     * @return 结果
     */
    public int deleteMediaTagByTagId(Long tagId);
}
