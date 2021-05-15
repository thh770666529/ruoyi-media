package com.ruoyi.media.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.media.mapper.WmActorMapper;
import com.ruoyi.media.domain.WmActor;
import com.ruoyi.media.service.IWmActorService;

/**
 * 演员Service业务层处理
 *
 * @author thh
 * @date 2021-05-15
 */
@Service
public class WmActorServiceImpl implements IWmActorService
{
    @Autowired
    private WmActorMapper wmActorMapper;

    /**
     * 查询演员
     *
     * @param actorId 演员ID
     * @return 演员
     */
    @Override
    public WmActor selectWmActorById(Long actorId)
    {
        return wmActorMapper.selectWmActorById(actorId);
    }

    /**
     * 查询演员列表
     *
     * @param wmActor 演员
     * @return 演员
     */
    @Override
    public List<WmActor> selectWmActorList(WmActor wmActor)
    {
        return wmActorMapper.selectWmActorList(wmActor);
    }

    /**
     * 新增演员
     *
     * @param wmActor 演员
     * @return 结果
     */
    @Override
    public int insertWmActor(WmActor wmActor)
    {
        return wmActorMapper.insertWmActor(wmActor);
    }

    /**
     * 修改演员
     *
     * @param wmActor 演员
     * @return 结果
     */
    @Override
    public int updateWmActor(WmActor wmActor)
    {
        return wmActorMapper.updateWmActor(wmActor);
    }

    /**
     * 批量删除演员
     *
     * @param actorIds 需要删除的演员ID
     * @return 结果
     */
    @Override
    public int deleteWmActorByIds(Long[] actorIds)
    {
        return wmActorMapper.deleteWmActorByIds(actorIds);
    }

    /**
     * 删除演员信息
     *
     * @param actorId 演员ID
     * @return 结果
     */
    @Override
    public int deleteWmActorById(Long actorId)
    {
        return wmActorMapper.deleteWmActorById(actorId);
    }
}
