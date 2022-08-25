package com.ruoyi.media.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.media.mapper.MediaTagMapper;
import com.ruoyi.media.domain.MediaTag;
import com.ruoyi.media.service.IMediaTagService;

/**
 * 电影标签Service业务层处理
 *
 * @author thh
 * @date 2021-09-11
 */
@Service
public class MediaTagServiceImpl implements IMediaTagService {
    @Autowired
    private MediaTagMapper mediaTagMapper;

    /**
     * 查询电影标签
     *
     * @param tagId 电影标签主键
     * @return 电影标签
     */
    @Override
    public MediaTag selectMediaTagByTagId(Long tagId) {
        return mediaTagMapper.selectMediaTagByTagId(tagId);
    }

    /**
     * 查询电影标签列表
     *
     * @param mediaTag 电影标签
     * @return 电影标签
     */
    @Override
    public List<MediaTag> selectMediaTagList(MediaTag mediaTag) {
        return mediaTagMapper.selectMediaTagList(mediaTag);
    }

    /**
     * 新增电影标签
     *
     * @param mediaTag 电影标签
     * @return 结果
     */
    @Override
    public int insertMediaTag(MediaTag mediaTag) {
        mediaTag.setCreateTime(DateUtils.getNowDate());
        return mediaTagMapper.insert(mediaTag);
    }

    /**
     * 修改电影标签
     *
     * @param mediaTag 电影标签
     * @return 结果
     */
    @Override
    public int updateMediaTag(MediaTag mediaTag) {
        mediaTag.setUpdateTime(DateUtils.getNowDate());
        return mediaTagMapper.updateById(mediaTag);
    }

    /**
     * 批量删除电影标签
     *
     * @param tagIds 需要删除的电影标签主键
     * @return 结果
     */
    @Override
    public int deleteMediaTagByTagIds(Long[] tagIds) {
        return mediaTagMapper.deleteMediaTagByTagIds(tagIds);
    }

    /**
     * 删除电影标签信息
     *
     * @param tagId 电影标签主键
     * @return 结果
     */
    @Override
    public int deleteMediaTagByTagId(Long tagId) {
        return mediaTagMapper.deleteById(tagId);
    }
}
