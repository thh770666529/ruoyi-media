package com.ruoyi.midea.mapper;

import java.util.List;
import com.ruoyi.midea.domain.WmMovie;
import com.ruoyi.midea.domain.WmMovieVideo;
import org.springframework.stereotype.Repository;

/**
 * 电影管理Mapper接口
 *
 * @author thh
 * @date 2021-05-01
 */
@Repository
public interface WmMovieMapper
{
    /**
     * 查询电影管理
     *
     * @param movieId 电影管理ID
     * @return 电影管理
     */
    public WmMovie selectWmMovieById(Long movieId);

    /**
     * 查询电影管理列表
     *
     * @param wmMovie 电影管理
     * @return 电影管理集合
     */
    public List<WmMovie> selectWmMovieList(WmMovie wmMovie);

    /**
     * 新增电影管理
     *
     * @param wmMovie 电影管理
     * @return 结果
     */
    public int insertWmMovie(WmMovie wmMovie);

    /**
     * 修改电影管理
     *
     * @param wmMovie 电影管理
     * @return 结果
     */
    public int updateWmMovie(WmMovie wmMovie);

    /**
     * 删除电影管理
     *
     * @param movieId 电影管理ID
     * @return 结果
     */
    public int deleteWmMovieById(Long movieId);

    /**
     * 批量删除电影管理
     *
     * @param movieIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmMovieByIds(Long[] movieIds);

    /**
     * 批量删除电影视频
     *
     * @param movieIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmMovieVideoByMovieIds(Long[] movieIds);

    /**
     * 批量新增电影视频
     *
     * @param wmMovieVideoList 电影视频列表
     * @return 结果
     */
    public int batchWmMovieVideo(List<WmMovieVideo> wmMovieVideoList);


    /**
     * 通过电影管理ID删除电影视频信息
     *
     * @param movieId 电影管理ID
     * @return 结果
     */
    public int deleteWmMovieVideoByMovieId(Long movieId);

    /**
     * 查询前几的热门电影
     * @param top
     * @return
     */
    List<WmMovie> selectHotMovieList(int top);

    /**
     * 查询视频详情
     * @param movieVideoId
     * @return
     */
    WmMovieVideo selectWmMovieVideoById(Long movieVideoId);
}
