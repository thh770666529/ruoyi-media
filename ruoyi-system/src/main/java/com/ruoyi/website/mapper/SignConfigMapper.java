package com.ruoyi.website.mapper;

import java.util.List;

import com.ruoyi.website.domain.SignConfig;

/**
 * 签到配置Mapper接口
 *
 * @author thh
 * @date 2021-12-11
 */
public interface SignConfigMapper {
    /**
     * 查询签到配置
     *
     * @param signConfigId 签到配置主键
     * @return 签到配置
     */
    public SignConfig selectSignConfigBySignConfigId(Long signConfigId);

    /**
     * 查询签到配置列表
     *
     * @param signConfig 签到配置
     * @return 签到配置集合
     */
    public List<SignConfig> selectSignConfigList(SignConfig signConfig);

    /**
     * 新增签到配置
     *
     * @param signConfig 签到配置
     * @return 结果
     */
    public int insertSignConfig(SignConfig signConfig);

    /**
     * 修改签到配置
     *
     * @param signConfig 签到配置
     * @return 结果
     */
    public int updateSignConfig(SignConfig signConfig);

    /**
     * 删除签到配置
     *
     * @param signConfigId 签到配置主键
     * @return 结果
     */
    public int deleteSignConfigBySignConfigId(Long signConfigId);

    /**
     * 批量删除签到配置
     *
     * @param signConfigIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSignConfigBySignConfigIds(Long[] signConfigIds);
}
