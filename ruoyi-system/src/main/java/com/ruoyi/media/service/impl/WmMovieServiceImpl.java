package com.ruoyi.media.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.media.domain.WmMovieVideo;
import com.ruoyi.media.mapper.WmMovieMapper;
import com.ruoyi.media.domain.WmMovie;
import com.ruoyi.media.service.IWmMovieService;

/**
 * 电影管理Service业务层处理
 *
 * @author thh
 * @date 2021-05-01
 */
@Service
public class WmMovieServiceImpl implements IWmMovieService
{
    @Autowired
    private WmMovieMapper wmMovieMapper;

    /**
     * 查询电影管理
     *
     * @param movieId 电影管理ID
     * @return 电影管理
     */
    @Override
    public WmMovie selectWmMovieById(Long movieId)
    {
        return wmMovieMapper.selectWmMovieById(movieId);
    }

    /**
     * 查询电影管理列表
     *
     * @param wmMovie 电影管理
     * @return 电影管理
     */
    @Override
    public List<WmMovie> selectWmMovieList(WmMovie wmMovie)
    {
        return wmMovieMapper.selectWmMovieList(wmMovie);
    }

    /**
     * 新增电影管理
     *
     * @param wmMovie 电影管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertWmMovie(WmMovie wmMovie)
    {
        wmMovie.setCreateTime(DateUtils.getNowDate());
        int rows = wmMovieMapper.insertWmMovie(wmMovie);
        insertWmMovieVideo(wmMovie);
        return rows;
    }

    /**
     * 修改电影管理
     *
     * @param wmMovie 电影管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateWmMovie(WmMovie wmMovie)
    {
        wmMovie.setUpdateTime(DateUtils.getNowDate());
        wmMovieMapper.deleteWmMovieVideoByMovieId(wmMovie.getMovieId());
        insertWmMovieVideo(wmMovie);
        return wmMovieMapper.updateWmMovie(wmMovie);
    }

    /**
     * 批量删除电影管理
     *
     * @param movieIds 需要删除的电影管理ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWmMovieByIds(Long[] movieIds)
    {
        wmMovieMapper.deleteWmMovieVideoByMovieIds(movieIds);
        return wmMovieMapper.deleteWmMovieByIds(movieIds);
    }

    /**
     * 删除电影管理信息
     *
     * @param movieId 电影管理ID
     * @return 结果
     */
    @Override
    public int deleteWmMovieById(Long movieId)
    {
        wmMovieMapper.deleteWmMovieVideoByMovieId(movieId);
        return wmMovieMapper.deleteWmMovieById(movieId);
    }

    @Override
    public List<WmMovie> selectHotMovieList(int top) {
        return  wmMovieMapper.selectHotMovieList(top);
    }

    @Override
    public WmMovieVideo selectWmMovieVideoById(Long movieVideoId) {

        return wmMovieMapper.selectWmMovieVideoById(movieVideoId);

    }

    /**
     * 新增电影视频信息
     *
     * @param wmMovie 电影管理对象
     */
    public void insertWmMovieVideo(WmMovie wmMovie)
    {
        List<WmMovieVideo> wmMovieVideoList = wmMovie.getWmMovieVideoList();
        Long movieId = wmMovie.getMovieId();
        if (StringUtils.isNotNull(wmMovieVideoList))
        {
            List<WmMovieVideo> list = new ArrayList<WmMovieVideo>();
            for (WmMovieVideo wmMovieVideo : wmMovieVideoList)
            {
                wmMovieVideo.setMovieId(movieId);
                list.add(wmMovieVideo);
            }
            if (list.size() > 0)
            {
                wmMovieMapper.batchWmMovieVideo(list);
            }
        }
    }
}
