package com.ruoyi.midea.service;

import java.util.List;
import com.ruoyi.midea.domain.WmMovie;

/**
 * 电影管理Service接口
 *
 * @author thh
 * @date 2021-05-01
 */
public interface IWmMovieService
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
     * 批量删除电影管理
     *
     * @param movieIds 需要删除的电影管理ID
     * @return 结果
     */
    public int deleteWmMovieByIds(Long[] movieIds);

    /**
     * 删除电影管理信息
     *
     * @param movieId 电影管理ID
     * @return 结果
     */
    public int deleteWmMovieById(Long movieId);

    /**
     * 查询前几的热门电影
     * @param top
     * @return
     */
    public List<WmMovie> selectHotMovieList(int top);
}
