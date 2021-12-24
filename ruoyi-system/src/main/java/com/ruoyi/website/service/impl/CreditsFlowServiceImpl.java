package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.CreditsFlowMapper;
import com.ruoyi.website.domain.CreditsFlow;
import com.ruoyi.website.service.ICreditsFlowService;

/**
 * 积分流水Service业务层处理
 * 
 * @author thh
 * @date 2021-12-11
 */
@Service
public class CreditsFlowServiceImpl implements ICreditsFlowService 
{
    @Autowired
    private CreditsFlowMapper creditsFlowMapper;

    /**
     * 查询积分流水
     * 
     * @param creditsId 积分流水主键
     * @return 积分流水
     */
    @Override
    public CreditsFlow selectCreditsFlowByCreditsId(Long creditsId)
    {
        return creditsFlowMapper.selectCreditsFlowByCreditsId(creditsId);
    }

    /**
     * 查询积分流水列表
     * 
     * @param creditsFlow 积分流水
     * @return 积分流水
     */
    @Override
    public List<CreditsFlow> selectCreditsFlowList(CreditsFlow creditsFlow)
    {
        return creditsFlowMapper.selectCreditsFlowList(creditsFlow);
    }

    /**
     * 新增积分流水
     * 
     * @param creditsFlow 积分流水
     * @return 结果
     */
    @Override
    public int insertCreditsFlow(CreditsFlow creditsFlow)
    {
        creditsFlow.setCreateTime(DateUtils.getNowDate());
        return creditsFlowMapper.insertCreditsFlow(creditsFlow);
    }

    /**
     * 修改积分流水
     * 
     * @param creditsFlow 积分流水
     * @return 结果
     */
    @Override
    public int updateCreditsFlow(CreditsFlow creditsFlow)
    {
        creditsFlow.setUpdateTime(DateUtils.getNowDate());
        return creditsFlowMapper.updateCreditsFlow(creditsFlow);
    }

    /**
     * 批量删除积分流水
     * 
     * @param creditsIds 需要删除的积分流水主键
     * @return 结果
     */
    @Override
    public int deleteCreditsFlowByCreditsIds(Long[] creditsIds)
    {
        return creditsFlowMapper.deleteCreditsFlowByCreditsIds(creditsIds);
    }

    /**
     * 删除积分流水信息
     * 
     * @param creditsId 积分流水主键
     * @return 结果
     */
    @Override
    public int deleteCreditsFlowByCreditsId(Long creditsId)
    {
        return creditsFlowMapper.deleteCreditsFlowByCreditsId(creditsId);
    }
}
