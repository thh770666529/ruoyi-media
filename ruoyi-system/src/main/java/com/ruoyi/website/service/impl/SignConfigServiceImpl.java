package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.SignConfigMapper;
import com.ruoyi.website.domain.SignConfig;
import com.ruoyi.website.service.ISignConfigService;

/**
 * 签到配置Service业务层处理
 * 
 * @author thh
 * @date 2021-12-11
 */
@Service
public class SignConfigServiceImpl implements ISignConfigService 
{
    @Autowired
    private SignConfigMapper signConfigMapper;

    /**
     * 查询签到配置
     * 
     * @param signConfigId 签到配置主键
     * @return 签到配置
     */
    @Override
    public SignConfig selectSignConfigBySignConfigId(Long signConfigId)
    {
        return signConfigMapper.selectSignConfigBySignConfigId(signConfigId);
    }

    /**
     * 查询签到配置列表
     * 
     * @param signConfig 签到配置
     * @return 签到配置
     */
    @Override
    public List<SignConfig> selectSignConfigList(SignConfig signConfig)
    {
        return signConfigMapper.selectSignConfigList(signConfig);
    }

    /**
     * 新增签到配置
     * 
     * @param signConfig 签到配置
     * @return 结果
     */
    @Override
    public int insertSignConfig(SignConfig signConfig)
    {
        signConfig.setCreateTime(DateUtils.getNowDate());
        return signConfigMapper.insertSignConfig(signConfig);
    }

    /**
     * 修改签到配置
     * 
     * @param signConfig 签到配置
     * @return 结果
     */
    @Override
    public int updateSignConfig(SignConfig signConfig)
    {
        signConfig.setUpdateTime(DateUtils.getNowDate());
        return signConfigMapper.updateSignConfig(signConfig);
    }

    /**
     * 批量删除签到配置
     * 
     * @param signConfigIds 需要删除的签到配置主键
     * @return 结果
     */
    @Override
    public int deleteSignConfigBySignConfigIds(Long[] signConfigIds)
    {
        return signConfigMapper.deleteSignConfigBySignConfigIds(signConfigIds);
    }

    /**
     * 删除签到配置信息
     * 
     * @param signConfigId 签到配置主键
     * @return 结果
     */
    @Override
    public int deleteSignConfigBySignConfigId(Long signConfigId)
    {
        return signConfigMapper.deleteSignConfigBySignConfigId(signConfigId);
    }
}
