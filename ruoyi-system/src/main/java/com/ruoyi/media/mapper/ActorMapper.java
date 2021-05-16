package com.ruoyi.media.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.media.domain.Actor;

/**
 * 演员Mapper接口
 *
 * @author thh
 * @date 2021-05-16
 */
public interface ActorMapper extends BaseMapper<Actor>
{
    /**
     * 查询演员
     *
     * @param actorId 演员ID
     * @return 演员
     */
    public Actor selectActorById(Long actorId);

    /**
     * 查询演员列表
     *
     * @param actor 演员
     * @return 演员集合
     */
    public List<Actor> selectActorList(Actor actor);

    /**
     * 新增演员
     *
     * @param actor 演员
     * @return 结果
     */
    public int insertActor(Actor actor);

    /**
     * 修改演员
     *
     * @param actor 演员
     * @return 结果
     */
    public int updateActor(Actor actor);

    /**
     * 删除演员
     *
     * @param actorId 演员ID
     * @return 结果
     */
    public int deleteActorById(Long actorId);

    /**
     * 批量删除演员
     *
     * @param actorIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteActorByIds(Long[] actorIds);
}
