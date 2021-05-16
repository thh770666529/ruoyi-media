package com.ruoyi.media.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.media.domain.Actor;
import com.ruoyi.media.domain.Movie;

/**
 * 演员Service接口
 *
 * @author thh
 * @date 2021-05-16
 */
public interface IActorService extends IService<Actor>
{

    /**
     * 查询演员列表
     *
     * @param actor 演员
     * @return 演员集合
     */
    public List<Actor> selectActorList(Actor actor);

}
