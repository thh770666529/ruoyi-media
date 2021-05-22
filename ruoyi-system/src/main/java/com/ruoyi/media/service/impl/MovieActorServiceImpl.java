package com.ruoyi.media.service.impl;

import java.util.List;

import com.ruoyi.media.domain.vo.MovieActorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.media.mapper.MovieActorMapper;
import com.ruoyi.media.domain.MovieActor;
import com.ruoyi.media.service.IMovieActorService;

/**
 * 电影演员关联Service业务层处理
 *
 * @author thh
 * @date 2021-05-16
 */
@Service
public class MovieActorServiceImpl implements IMovieActorService
{
    @Autowired
    private MovieActorMapper movieActorMapper;

    /**
     * 查询电影演员关联
     *
     * @param actorId 电影演员关联ID
     * @return 电影演员关联
     */
    @Override
    public MovieActor selectMovieActorById(Long actorId)
    {
        return movieActorMapper.selectMovieActorById(actorId);
    }

    /**
     * 查询电影演员关联列表
     *
     * @param movieActorVO 电影演员关联
     * @return 电影演员关联
     */
    @Override
    public List<MovieActorVO> selectMovieActorList(MovieActorVO movieActorVO)
    {
        return movieActorMapper.selectMovieActorList(movieActorVO);
    }

    /**
     * 新增电影演员关联
     *
     * @param movieActor 电影演员关联
     * @return 结果
     */
    @Override
    public int insertMovieActor(MovieActor movieActor)
    {
        return movieActorMapper.insertMovieActor(movieActor);
    }

    /**
     * 修改电影演员关联
     *
     * @param movieActor 电影演员关联
     * @return 结果
     */
    @Override
    public int updateMovieActor(MovieActor movieActor)
    {
        return movieActorMapper.updateMovieActor(movieActor);
    }

    /**
     * 批量删除电影演员关联
     *
     * @param actorIds 需要删除的电影演员关联ID
     * @return 结果
     */
    @Override
    public int deleteMovieActorByIds(Long[] actorIds)
    {
        return movieActorMapper.deleteMovieActorByIds(actorIds);
    }

    /**
     * 删除电影演员关联信息
     *
     * @param actorId 电影演员关联ID
     * @return 结果
     */
    @Override
    public int deleteMovieActorById(Long actorId)
    {
        return movieActorMapper.deleteMovieActorById(actorId);
    }
}
