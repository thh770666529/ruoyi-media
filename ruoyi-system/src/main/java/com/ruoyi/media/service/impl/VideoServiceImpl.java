package com.ruoyi.media.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.mapper.MovieMapper;
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
public class VideoServiceImpl  extends ServiceImpl<VideoMapper, Video> implements IVideoService
{
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> selectVideoList(Video video) {
        return videoMapper.selectVideoList(video);
    }
}
