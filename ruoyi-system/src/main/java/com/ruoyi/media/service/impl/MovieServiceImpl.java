package com.ruoyi.media.service.impl;


import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.MovieActorType;
import com.ruoyi.media.domain.MovieActor;
import com.ruoyi.media.domain.vo.MovieActorVO;
import com.ruoyi.media.domain.vo.MovieVO;
import com.ruoyi.media.mapper.MovieActorMapper;
import com.ruoyi.media.mapper.VideoMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.util.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

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

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private MovieActorMapper movieActorMapper;

    @Autowired
    TokenUtil tokenUtil;
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
            String publishBy = movieVO.getPublishBy();
            if (StringUtils.isNotEmpty(publishBy)){
                SysUser sysUser = sysUserMapper.selectUserByUserName(publishBy);
                movieVO.setPublishUsername(sysUser!=null?sysUser.getNickName():null);
            }
            Video video = new Video();
            video.setMovieId(movieId);
            List<Video> videoList = videoMapper.selectVideoList(video);
            movieVO.setVideoList(videoList);

            MovieActorVO actorVO = new MovieActorVO();
            actorVO.setMovieId(movieId);
            actorVO.setType(MovieActorType.ACTOR.getValue());
            List<MovieActorVO> movieActorList = movieActorMapper.selectMovieActorList(actorVO);
            movieVO.setActorList(movieActorList);

            MovieActorVO  directorVO = new MovieActorVO();
            directorVO.setMovieId(movieId);
            directorVO.setType(MovieActorType.DIRECTOR.getValue());
            List<MovieActorVO> directorList = movieActorMapper.selectMovieActorList(directorVO);
            movieVO.setDirectorList(directorList);

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
        int rows = movieMapper.insert(movieVO);
        this.insertVideo(movieVO);
        this.insertActor(movieVO,MovieActorType.ACTOR);
        this.insertActor(movieVO,MovieActorType.DIRECTOR);
        return rows;
    }

    /**
     * @Desc 更新演员
     * @param movieVO
     */
    private void insertActor(MovieVO movieVO,MovieActorType movieActorType) {
        Long movieId = movieVO.getMovieId();
        List<MovieActorVO> actorList = movieVO.getActorList();
        List<MovieActorVO> directorList = movieVO.getDirectorList();

        List<MovieActorVO> insertList = null;
       switch (movieActorType){
           case ACTOR:
               insertList = actorList;
               break;
           case DIRECTOR:
               insertList = directorList;
               break;
               default:
                   return;
       }
        if (StringUtils.isNotNull(insertList))
        {
            for (MovieActorVO movieActorVO : insertList) {
                MovieActor movieActor = new MovieActor();
                BeanUtils.copyProperties(movieActorVO,movieActor);
                movieActor.setMovieId(movieId);
                movieActor.setType(movieActorType.getValue());
                movieActorMapper.insertMovieActor(movieActor);
            }
        }
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
        videoMapper.deleteByMovieId(movieVO.getMovieId());
        this.insertVideo(movieVO);
        Map movieActorMap  = new HashMap<>();
        movieActorMap.put("movie_id",movieVO.getMovieId());
        movieActorMapper.deleteByMap(movieActorMap);
        this.insertActor(movieVO,MovieActorType.ACTOR);
        this.insertActor(movieVO,MovieActorType.DIRECTOR);
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

    @Override
    public int removeActorList(Long[] actorIds) {
        return movieActorMapper.deleteMovieActorByIds(actorIds);
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
