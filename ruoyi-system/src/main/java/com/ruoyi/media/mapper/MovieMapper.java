package com.ruoyi.media.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.domain.vo.MovieVO;
import org.springframework.stereotype.Repository;

/**
 * 电影Mapper接口
 *
 * @author thh
 * @date 2021-05-16
 */
@Repository
public interface MovieMapper extends BaseMapper<Movie>
{

    /**
     * 查询电影列表
     *
     * @param movie 电影
     * @return 电影集合
     */
    public List<MovieVO> selectMovieList(Movie movie);



    /**
     * 查询前几的热门电影
     * @param top
     * @return
     */
    List<MovieVO> selectHotMovieList(int top);

    /**
     * 查询演员相关电影
     * @param actorId
     * @return
     */
    List<MovieVO> selectListByActorId(Long actorId);

    /**
     * 门户查询电影
     *
     * @param movieId 电影ID
     * @return 电影
     */
    MovieVO selectWebMovieById(Long movieId);

    /**
     * 门户查询电影列表
     * @param movieVO
     * @return
     */
    List<MovieVO> selectWebMovieList(MovieVO movieVO);
}
