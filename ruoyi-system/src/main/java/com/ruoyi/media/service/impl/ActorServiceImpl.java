package com.ruoyi.media.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.media.mapper.ActorMapper;
import com.ruoyi.media.domain.Actor;
import com.ruoyi.media.service.IActorService;

/**
 * 演员Service业务层处理
 * 
 * @author thh
 * @date 2021-05-16
 */
@Service
public class ActorServiceImpl implements IActorService 
{
    @Autowired
    private ActorMapper actorMapper;

    /**
     * 查询演员
     * 
     * @param actorId 演员ID
     * @return 演员
     */
    @Override
    public Actor selectActorById(Long actorId)
    {
        return actorMapper.selectActorById(actorId);
    }

    /**
     * 查询演员列表
     * 
     * @param actor 演员
     * @return 演员
     */
    @Override
    public List<Actor> selectActorList(Actor actor)
    {
        return actorMapper.selectActorList(actor);
    }

    /**
     * 新增演员
     * 
     * @param actor 演员
     * @return 结果
     */
    @Override
    public int insertActor(Actor actor)
    {
        return actorMapper.insertActor(actor);
    }

    /**
     * 修改演员
     * 
     * @param actor 演员
     * @return 结果
     */
    @Override
    public int updateActor(Actor actor)
    {
        return actorMapper.updateActor(actor);
    }

    /**
     * 批量删除演员
     * 
     * @param actorIds 需要删除的演员ID
     * @return 结果
     */
    @Override
    public int deleteActorByIds(Long[] actorIds)
    {
        return actorMapper.deleteActorByIds(actorIds);
    }

    /**
     * 删除演员信息
     * 
     * @param actorId 演员ID
     * @return 结果
     */
    @Override
    public int deleteActorById(Long actorId)
    {
        return actorMapper.deleteActorById(actorId);
    }
}
