package com.ruoyi.media.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.media.domain.vo.MovieVO;
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
public class MovieServiceImpl implements IMovieService
{
    @Autowired
    private MovieMapper movieMapper;

    /**
     * 查询电影
     *
     * @param movieId 电影ID
     * @return 电影
     */
    @Override
    public MovieVO selectMovieById(Long movieId)
    {
        return movieMapper.selectMovieById(movieId);
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
        int rows = movieMapper.insertMovie(movieVO);
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
        movieMapper.deleteVideoByMovieId(movieVO.getMovieId());
        insertVideo(movieVO);
        return movieMapper.updateMovie(movieVO);
    }

    /**
     * 批量删除电影
     *
     * @param movieIds 需要删除的电影ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMovieByIds(Long[] movieIds)
    {
        movieMapper.deleteVideoByVideoIds(movieIds);
        return movieMapper.deleteMovieByIds(movieIds);
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
        movieMapper.deleteVideoByMovieId(movieId);
        return movieMapper.deleteMovieById(movieId);
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
                movieMapper.batchVideo(list);
            }
        }
    }
}
