package com.ruoyi.media.service;

import java.util.List;
import com.ruoyi.media.domain.WmActor;

/**
 * 演员Service接口
 *
 * @author thh
 * @date 2021-05-15
 */
public interface IWmActorService
{
    /**
     * 查询演员
     *
     * @param actorId 演员ID
     * @return 演员
     */
    public WmActor selectWmActorById(Long actorId);

    /**
     * 查询演员列表
     *
     * @param wmActor 演员
     * @return 演员集合
     */
    public List<WmActor> selectWmActorList(WmActor wmActor);

    /**
     * 新增演员
     *
     * @param wmActor 演员
     * @return 结果
     */
    public int insertWmActor(WmActor wmActor);

    /**
     * 修改演员
     *
     * @param wmActor 演员
     * @return 结果
     */
    public int updateWmActor(WmActor wmActor);

    /**
     * 批量删除演员
     *
     * @param actorIds 需要删除的演员ID
     * @return 结果
     */
    public int deleteWmActorByIds(Long[] actorIds);

    /**
     * 删除演员信息
     *
     * @param actorId 演员ID
     * @return 结果
     */
    public int deleteWmActorById(Long actorId);
}
