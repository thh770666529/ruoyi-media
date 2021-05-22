package com.ruoyi.media.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.domain.Video;
import org.springframework.stereotype.Repository;

/**
 * 电影视频Mapper接口
 *
 * @author thh
 * @date 2021-05-16
 */
@Repository
public interface VideoMapper extends BaseMapper<Video>
{
    /**
     * 查询电影视频列表
     *
     * @param video 电影视频
     * @return 电影视频集合
     */
    public List<Video> selectVideoList(Video video);

    /**
     * 批量新增电影视频
     *
     * @param videoList 电影视频列表
     * @return 结果
     */
    public int batchVideo(List<Video> videoList);


    /**
     * 根据电影Id删除视频
     * @param movieId
     * @return
     */
    public int deleteByMovieId(Long movieId);
}
