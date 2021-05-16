package com.ruoyi.media.mapper;

import java.util.List;
import com.ruoyi.media.domain.Video;

/**
 * 电影视频Mapper接口
 *
 * @author thh
 * @date 2021-05-16
 */
public interface VideoMapper
{
    /**
     * 查询电影视频
     *
     * @param videoId 电影视频ID
     * @return 电影视频
     */
    public Video selectVideoById(Long videoId);

    /**
     * 查询电影视频列表
     *
     * @param video 电影视频
     * @return 电影视频集合
     */
    public List<Video> selectVideoList(Video video);

    /**
     * 新增电影视频
     *
     * @param video 电影视频
     * @return 结果
     */
    public int insertVideo(Video video);

    /**
     * 修改电影视频
     *
     * @param video 电影视频
     * @return 结果
     */
    public int updateVideo(Video video);

    /**
     * 删除电影视频
     *
     * @param videoId 电影视频ID
     * @return 结果
     */
    public int deleteVideoById(Long videoId);

    /**
     * 批量删除电影视频
     *
     * @param movieVideoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteVideoByIds(Long[] movieVideoIds);
}
