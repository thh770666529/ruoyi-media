package com.ruoyi.website.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.website.domain.SignRecord;
import com.ruoyi.website.domain.vo.SignRecordVO;
import org.springframework.stereotype.Repository;

/**
 * 签到日志Mapper接口
 *
 * @author thh
 * @date 2021-12-11
 */
@Repository
public interface SignRecordMapper {
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
     * 删除签到日志
     *
     * @param signRecordId 签到日志主键
     * @return 结果
     */
    public int deleteSignRecordBySignRecordId(Long signRecordId);

    /**
     * 批量删除签到日志
     *
     * @param signRecordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSignRecordBySignRecordIds(Long[] signRecordIds);


    /**
     * 获取更新的签到数据
     *
     * @param map
     * @return
     */
    Map<String, Object> getMyNewSignRecord(Map map);
}
