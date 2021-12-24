package com.ruoyi.website.service;

import java.util.List;
import com.ruoyi.website.domain.SignRecord;
import com.ruoyi.website.domain.vo.SignRecordVO;

/**
 * 签到日志Service接口
 *
 * @author thh
 * @date 2021-12-11
 */
public interface ISignRecordService
{
    /**
     * 查询签到日志
     *
     * @param signRecordId 签到日志主键
     * @return 签到日志
     */
    public SignRecord selectSignRecordBySignRecordId(Long signRecordId);

    /**
     * 查询签到日志列表
     *
     * @param signRecord 签到日志
     * @return 签到日志集合
     */
    public List<SignRecord> selectSignRecordList(SignRecord signRecord);

    /**
     * 新增签到日志
     *
     * @param signRecord 签到日志
     * @return 结果
     */
    public int insertSignRecord(SignRecord signRecord);

    /**
     * 修改签到日志
     *
     * @param signRecord 签到日志
     * @return 结果
     */
    public int updateSignRecord(SignRecord signRecord);

    /**
     * 批量删除签到日志
     *
     * @param signRecordIds 需要删除的签到日志主键集合
     * @return 结果
     */
    public int deleteSignRecordBySignRecordIds(Long[] signRecordIds);

    /**
     * 删除签到日志信息
     *
     * @param signRecordId 签到日志主键
     * @return 结果
     */
    public int deleteSignRecordBySignRecordId(Long signRecordId);

    /**
     * 获取签到数据
     * @return
     */
    SignRecordVO getSignRecord();

    /**
     * 签到
     * @return
     */
    SignRecordVO sign();
}
