package com.ruoyi.media.service;

import java.util.List;

import com.ruoyi.media.domain.PlayLogs;

/**
 * 播放记录Service接口
 *
 * @author thh
 * @date 2021-12-05
 */
public interface IPlayLogsService {

    /**
     * 查询播放记录列表
     *
     * @param playLogs 播放记录
     * @return 播放记录集合
     */
    public List<PlayLogs> selectPlayLogsList(PlayLogs playLogs);

    /**
     * 新增和更新播放记录
     *
     * @param playLogs 播放记录
     * @return 结果
     */
    public int insertOrUpdatePalyLogs(PlayLogs playLogs);


    /**
     * @param videoId 视频id  userId用户id
     * @return 结果
     */
    PlayLogs selectPlayLogsByVideoId(String videoId);
}
