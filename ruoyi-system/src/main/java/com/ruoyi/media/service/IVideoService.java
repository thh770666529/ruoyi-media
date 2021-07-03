package com.ruoyi.media.service;

import java.io.IOException;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.vo.NetWorkDiskVO;
import com.ruoyi.media.vo.UploadVideoVO;

/**
 * 电影视频Service接口
 *
 * @author thh
 * @date 2021-05-16
 */
public interface IVideoService extends IService<Video>
{

    public List<Video> selectVideoList(Video video);

    /**
     * 通过网盘上传视频
     *@param netWorkDiskVO
     * @return
     */
    public UploadVideoVO uploadVideoByNetWorkDisk(NetWorkDiskVO netWorkDiskVO);
}
