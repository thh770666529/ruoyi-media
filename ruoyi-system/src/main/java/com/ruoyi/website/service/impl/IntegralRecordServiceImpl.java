package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.IntegralRecordMapper;
import com.ruoyi.website.domain.IntegralRecord;
import com.ruoyi.website.service.IIntegralRecordService;

/**
 * 积分记录Service业务层处理
 * 
 * @author thh
 * @date 2022-05-13
 */
@Service
public class IntegralRecordServiceImpl implements IIntegralRecordService 
{
    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    /**
     * 查询积分记录
     * 
     * @param integralRecordId 积分记录主键
     * @return 积分记录
     */
    @Override
    public IntegralRecord selectIntegralRecordByIntegralRecordId(Long integralRecordId)
    {
        return integralRecordMapper.selectIntegralRecordByIntegralRecordId(integralRecordId);
    }

    /**
     * 查询积分记录列表
     * 
     * @param integralRecord 积分记录
     * @return 积分记录
     */
    @Override
    public List<IntegralRecord> selectIntegralRecordList(IntegralRecord integralRecord)
    {
        return integralRecordMapper.selectIntegralRecordList(integralRecord);
    }

    /**
     * 新增积分记录
     * 
     * @param integralRecord 积分记录
     * @return 结果
     */
    @Override
    public int insertIntegralRecord(IntegralRecord integralRecord)
    {
        integralRecord.setCreateTime(DateUtils.getNowDate());
        return integralRecordMapper.insertIntegralRecord(integralRecord);
    }

    /**
     * 修改积分记录
     * 
     * @param integralRecord 积分记录
     * @return 结果
     */
    @Override
    public int updateIntegralRecord(IntegralRecord integralRecord)
    {
        integralRecord.setUpdateTime(DateUtils.getNowDate());
        return integralRecordMapper.updateIntegralRecord(integralRecord);
    }

    /**
     * 批量删除积分记录
     * 
     * @param integralRecordIds 需要删除的积分记录主键
     * @return 结果
     */
    @Override
    public int deleteIntegralRecordByIntegralRecordIds(Long[] integralRecordIds)
    {
        return integralRecordMapper.deleteIntegralRecordByIntegralRecordIds(integralRecordIds);
    }

    /**
     * 删除积分记录信息
     * 
     * @param integralRecordId 积分记录主键
     * @return 结果
     */
    @Override
    public int deleteIntegralRecordByIntegralRecordId(Long integralRecordId)
    {
        return integralRecordMapper.deleteIntegralRecordByIntegralRecordId(integralRecordId);
    }
}
