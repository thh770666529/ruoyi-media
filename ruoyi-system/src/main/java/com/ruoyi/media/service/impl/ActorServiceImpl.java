package com.ruoyi.media.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.media.mapper.MovieMapper;
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
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor> implements IActorService
{
    @Autowired
    private ActorMapper actorMapper;


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
     * 查询热门演员
     * @param top
     * @return
     */
    @Override
    public List<Actor> selectHotActorList(int top) {
        return actorMapper.selectHotActorList(top);
    }

    @Override
    public List<Actor> selectNotSelectedList(Actor actor,Long[] ids) {
        return actorMapper.selectNotSelectedListByIds(actor,ids);
    }

}
