package com.ruoyi.media.service.impl;


import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.media.domain.vo.MovieVO;
import com.ruoyi.media.mapper.VideoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.mapper.MovieMapper;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.service.IMovieService;

/**
 * 电影Service业务层处理
 *
 * @author thh
 * @date 2021-05-16
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService
{
    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private VideoMapper videoMapper;
    /**
     * 查询电影
     *
     * @param movieId 电影ID
     * @return 电影
     */
    @Override
    public MovieVO selectMovieById(Long movieId)
    {
        Movie movie = movieMapper.selectById(movieId);
        MovieVO movieVO = new MovieVO();
        if (movie!=null){
            BeanUtils.copyProperties(movie,movieVO);
            Video video = new Video();
            video.setMovieId(movieId);
            List<Video> videoList = videoMapper.selectVideoList(video);
            movieVO.setVideoList(videoList);
        }
        return movieVO;
    }

    /**
     * 查询电影列表
     *
     * @param movie 电影
     * @return 电影
     */
    @Override
    public List<MovieVO> selectMovieList(Movie movie)
    {
        return movieMapper.selectMovieList(movie);
    }

    /**
     * 新增电影
     *
     * @param movieVO 电影
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMovie(MovieVO movieVO)
    {
        movieVO.setCreateTime(DateUtils.getNowDate());
        int rows = movieMapper.insert(movieVO);
        insertVideo(movieVO);
        return rows;
    }

    /**
     * 修改电影
     *
     * @param movieVO 电影
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMovie(MovieVO movieVO)
    {
        movieVO.setUpdateTime(DateUtils.getNowDate());
        videoMapper.deleteByMovieId(movieVO.getMovieId());
        insertVideo(movieVO);
        return  movieMapper.updateById(movieVO);
    }


    /**
     * 删除电影信息
     *
     * @param movieId 电影ID
     * @return 结果
     */
    @Override
    public int deleteMovieById(Long movieId)
    {
        videoMapper.deleteByMovieId(movieId);
        return movieMapper.deleteById(movieId);
    }

    @Override
    public List<Movie> selectHotMovieList(int top) {
        return movieMapper.selectHotMovieList(top);
    }

    /**
     * 新增电影视频信息
     *
     * @param movieVO 电影对象
     */
    public void insertVideo(MovieVO movieVO)
    {
        List<Video> videoList = movieVO.getVideoList();
        Long movieId = movieVO.getMovieId();
        if (StringUtils.isNotNull(videoList))
        {
            List<Video> list = new ArrayList<Video>();
            for (Video video : videoList)
            {
                video.setMovieId(movieId);
                list.add(video);
            }
            if (list.size() > 0)
            {
                videoMapper.batchVideo(list);
            }
        }
    }
}
