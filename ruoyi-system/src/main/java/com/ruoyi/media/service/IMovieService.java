package com.ruoyi.media.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.domain.vo.MovieVO;

/**
 * 电影Service接口
 *
 * @author thh
 * @date 2021-05-16
 */
public interface IMovieService extends IService<Movie>
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
     * @param movieVO 电影
     * @return 结果
     */
    public int insertMovie(MovieVO movieVO);

    /**
     * 修改电影
     *
     * @param movieVO 电影
     * @return 结果
     */
    public int updateMovie(MovieVO movieVO);


    /**
     * 删除电影信息
     *
     * @param movieId 电影ID
     * @return 结果
     */
    public int deleteMovieById(Long movieId);

    /**
     * 查询热门电影
     * @param top
     * @return
     */
    List<Movie> selectHotMovieList(int top);

    /**
     * 删除演员关联
     * @param actorIds
     * @return
     */
    int removeActorList(Long[] actorIds);
}
