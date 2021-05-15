package com.ruoyi.media.mapper;

import java.util.List;
import com.ruoyi.media.domain.WmActor;

/**
 * 演员Mapper接口
 *
 * @author thh
 * @date 2021-05-15
 */
public interface WmActorMapper
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
     * 删除演员
     *
     * @param actorId 演员ID
     * @return 结果
     */
    public int deleteWmActorById(Long actorId);

    /**
     * 批量删除演员
     *
     * @param actorIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmActorByIds(Long[] actorIds);
}
