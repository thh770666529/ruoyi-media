package com.ruoyi.media.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.media.mapper.VideoMapper;
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.service.IVideoService;

/**
 * 电影视频Service业务层处理
 *
 * @author thh
 * @date 2021-05-16
 */
@Service
public class VideoServiceImpl implements IVideoService
{
    @Autowired
    private VideoMapper videoMapper;

    /**
     * 查询电影视频
     *
     * @param videoId 电影视频ID
     * @return 电影视频
     */
    @Override
    public Video selectVideoById(Long videoId)
    {
        return videoMapper.selectVideoById(videoId);
    }

    /**
     * 查询电影视频列表
     *
     * @param video 电影视频
     * @return 电影视频
     */
    @Override
    public List<Video> selectVideoList(Video video)
    {
        return videoMapper.selectVideoList(video);
    }

    /**
     * 新增电影视频
     *
     * @param video 电影视频
     * @return 结果
     */
    @Override
    public int insertVideo(Video video)
    {
        video.setCreateTime(DateUtils.getNowDate());
        return videoMapper.insertVideo(video);
    }

    /**
     * 修改电影视频
     *
     * @param video 电影视频
     * @return 结果
     */
    @Override
    public int updateVideo(Video video)
    {
        video.setUpdateTime(DateUtils.getNowDate());
        return videoMapper.updateVideo(video);
    }

    /**
     * 批量删除电影视频
     *
     * @param movieVideoIds 需要删除的电影视频ID
     * @return 结果
     */
    @Override
    public int deleteVideoByIds(Long[] movieVideoIds)
    {
        return videoMapper.deleteVideoByIds(movieVideoIds);
    }

    /**
     * 删除电影视频信息
     *
     * @param videoId 电影视频ID
     * @return 结果
     */
    @Override
    public int deleteVideoById(Long videoId)
    {
        return videoMapper.deleteVideoById(videoId);
    }
}
