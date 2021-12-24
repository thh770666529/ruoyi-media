package com.ruoyi.website.service;

import java.util.List;
import com.ruoyi.website.domain.CreditsFlow;

/**
 * 积分流水Service接口
 * 
 * @author thh
 * @date 2021-12-11
 */
public interface ICreditsFlowService 
{
    /**
     * 查询积分流水
     * 
     * @param creditsId 积分流水主键
     * @return 积分流水
     */
    public CreditsFlow selectCreditsFlowByCreditsId(Long creditsId);

    /**
     * 查询积分流水列表
     * 
     * @param creditsFlow 积分流水
     * @return 积分流水集合
     */
    public List<CreditsFlow> selectCreditsFlowList(CreditsFlow creditsFlow);

    /**
     * 新增积分流水
     * 
     * @param creditsFlow 积分流水
     * @return 结果
     */
    public int insertCreditsFlow(CreditsFlow creditsFlow);

    /**
     * 修改积分流水
     * 
     * @param creditsFlow 积分流水
     * @return 结果
     */
    public int updateCreditsFlow(CreditsFlow creditsFlow);

    /**
     * 批量删除积分流水
     * 
     * @param creditsIds 需要删除的积分流水主键集合
     * @return 结果
     */
    public int deleteCreditsFlowByCreditsIds(Long[] creditsIds);

    /**
     * 删除积分流水信息
     * 
     * @param creditsId 积分流水主键
     * @return 结果
     */
    public int deleteCreditsFlowByCreditsId(Long creditsId);
}
