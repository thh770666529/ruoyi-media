package com.ruoyi.website.mapper;

import java.util.List;

import com.ruoyi.website.domain.IntegralRecord;

/**
 * 积分记录Mapper接口
 *
 * @author thh
 * @date 2022-05-13
 */
public interface IntegralRecordMapper {
    /**
     * 查询积分记录
     *
     * @param integralRecordId 积分记录主键
     * @return 积分记录
     */
    public IntegralRecord selectIntegralRecordByIntegralRecordId(Long integralRecordId);

    /**
     * 查询积分记录列表
     *
     * @param integralRecord 积分记录
     * @return 积分记录集合
     */
    public List<IntegralRecord> selectIntegralRecordList(IntegralRecord integralRecord);

    /**
     * 新增积分记录
     *
     * @param integralRecord 积分记录
     * @return 结果
     */
    public int insertIntegralRecord(IntegralRecord integralRecord);

    /**
     * 修改积分记录
     *
     * @param integralRecord 积分记录
     * @return 结果
     */
    public int updateIntegralRecord(IntegralRecord integralRecord);

    /**
     * 删除积分记录
     *
     * @param integralRecordId 积分记录主键
     * @return 结果
     */
    public int deleteIntegralRecordByIntegralRecordId(Long integralRecordId);

    /**
     * 批量删除积分记录
     *
     * @param integralRecordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIntegralRecordByIntegralRecordIds(Long[] integralRecordIds);
}
