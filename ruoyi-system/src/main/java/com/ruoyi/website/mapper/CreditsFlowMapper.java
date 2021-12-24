package com.ruoyi.website.mapper;

import java.util.List;
import com.ruoyi.website.domain.CreditsFlow;

/**
 * 积分流水Mapper接口
 *
 * @author thh
 * @date 2021-12-11
 */
public interface CreditsFlowMapper
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
     * 删除积分流水
     *
     * @param creditsId 积分流水主键
     * @return 结果
     */
    public int deleteCreditsFlowByCreditsId(Long creditsId);

    /**
     * 批量删除积分流水
     *
     * @param creditsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCreditsFlowByCreditsIds(Long[] creditsIds);
    }
