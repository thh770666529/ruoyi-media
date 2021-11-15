package com.ruoyi.media.service;

import java.io.IOException;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.index.vo.SearchParamVO;
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
     * 门户查询电影
     *
     * @param movieId 电影ID
     * @return 电影
     */
    public MovieVO selectWebMovieById(Long movieId);


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
    public int insertMovie(MovieVO movieVO) throws IOException;

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
    List<MovieVO> selectHotMovieList(int top);

    /**
     * 删除演员关联
     * @param actorIds
     * @return
     */
    int removeActorList(Long[] actorIds);

    /**
     * 获取演员参演的电影
     * @param actorId
     * @return
     */
    List<MovieVO> getListByActorId(Long actorId);

    /**
     * 获取相关类型的电影
     * @param movieId 电影id
     * @return
     */
    List<Movie> getSameTypeMovieList(Long movieId);

    /**
     * 查询门户列表
     * @param movieVO
     * @return
     */
    List<MovieVO> selectWebMovieList(MovieVO movieVO);

    /**
     * 搜索门户
     * @param searchParamVO
     * @return
     */
    List<MovieVO> search(SearchParamVO searchParamVO);
}
