package com.ruoyi.media.mapper;

import java.util.List;
import com.ruoyi.media.domain.PlayLogs;
import org.apache.ibatis.annotations.Param;

/**
 * 播放记录Mapper接口
 *
 * @author thh
 * @date 2021-12-05
 */
public interface PlayLogsMapper
{
    /**
     * 查询播放记录
     *
     * @param playLogId 播放记录主键
     * @return 播放记录
     */
    public PlayLogs selectPlayLogsByPlayLogId(Long playLogId);

    /**
     * 查询播放记录列表
     *
     * @param playLogs 播放记录
     * @return 播放记录集合
     */
    public List<PlayLogs> selectPlayLogsList(PlayLogs playLogs);

    /**
     * 新增播放记录
     *
     * @param playLogs 播放记录
     * @return 结果
     */
    public int insertPlayLogs(PlayLogs playLogs);

    /**
     * 修改播放记录
     *
     * @param playLogs 播放记录
     * @return 结果
     */
    public int updatePlayLogs(PlayLogs playLogs);

    /**
     * 删除播放记录
     *
     * @param playLogId 播放记录主键
     * @return 结果
     */
    public int deletePlayLogsByPlayLogId(Long playLogId);

    /**
     * 批量删除播放记录
     *
     * @param playLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePlayLogsByPlayLogIds(Long[] playLogIds);


    /**
     * 根据条件查询明细
     * @param playLogs 条件
     * @return 结果
     */
    PlayLogs selectPlayLogsByCondition(PlayLogs playLogs);
}
