package com.ruoyi.media.mapper;

import java.util.List;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.domain.vo.MovieVO;

/**
 * 电影Mapper接口
 *
 * @author thh
 * @date 2021-05-16
 */
public interface MovieMapper
{
    /**
     * 查询电影
     *
     * @param movieId 电影ID
     * @return 电影
     */
    public MovieVO selectMovieById(Long movieId);

    /**
     * 查询电影列表
     *
     * @param movie 电影
     * @return 电影集合
     */
    public List<MovieVO> selectMovieList(Movie movie);

    /**
     * 新增电影
     *
     * @param movie 电影
     * @return 结果
     */
    public int insertMovie(Movie movie);

    /**
     * 修改电影
     *
     * @param movie 电影
     * @return 结果
     */
    public int updateMovie(Movie movie);

    /**
     * 删除电影
     *
     * @param movieId 电影ID
     * @return 结果
     */
    public int deleteMovieById(Long movieId);

    /**
     * 批量删除电影
     *
     * @param movieIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMovieByIds(Long[] movieIds);

    /**
     * 批量删除电影视频
     *
     * @param movieIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteVideoByVideoIds(Long[] movieIds);

    /**
     * 批量新增电影视频
     *
     * @param videoList 电影视频列表
     * @return 结果
     */
    public int batchVideo(List<Video> videoList);


    /**
     * 通过电影ID删除电影视频信息
     *
     * @param movieId 电影ID
     * @return 结果
     */
    public int deleteVideoByMovieId(Long movieId);



    /**
     * 查询前几的热门电影
     * @param top
     * @return
     */
    List<Movie> selectHotMovieList(int top);

    /**
     * 查询视频详情
     * @param videoId
     * @return
     */
    Video selectVideoById(Long videoId);


    List<Video> selectVideoListByMovieId(Long movieId);
}
