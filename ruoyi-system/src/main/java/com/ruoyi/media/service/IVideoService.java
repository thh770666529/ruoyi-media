package com.ruoyi.media.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.domain.Video;

/**
 * 电影视频Service接口
 *
 * @author thh
 * @date 2021-05-16
 */
public interface IVideoService extends IService<Video>
{

    List<Video> selectVideoList(Video video);
}
