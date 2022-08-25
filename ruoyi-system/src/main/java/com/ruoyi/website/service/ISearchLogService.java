package com.ruoyi.website.service;

import java.util.List;

import com.ruoyi.website.domain.SearchLog;

/**
 * 搜索日志Service接口
 *
 * @author thh
 * @date 2021-12-11
 */
public interface ISearchLogService {
    /**
     * 查询搜索日志
     *
     * @param searchLogId 搜索日志主键
     * @return 搜索日志
     */
    public SearchLog selectSearchLogBySearchLogId(Long searchLogId);

    /**
     * 查询搜索日志列表
     *
     * @param searchLog 搜索日志
     * @return 搜索日志集合
     */
    public List<SearchLog> selectSearchLogList(SearchLog searchLog);

    /**
     * 新增搜索日志
     *
     * @param keyword 关键字
     * @return 结果
     */
    public int insertSearchLog(String keyword);

    /**
     * 修改搜索日志
     *
     * @param searchLog 搜索日志
     * @return 结果
     */
    public int updateSearchLog(SearchLog searchLog);

    /**
     * 批量删除搜索日志
     *
     * @param searchLogIds 需要删除的搜索日志主键集合
     * @return 结果
     */
    public int deleteSearchLogBySearchLogIds(Long[] searchLogIds);

    /**
     * 删除搜索日志信息
     *
     * @param searchLogId 搜索日志主键
     * @return 结果
     */
    public int deleteSearchLogBySearchLogId(Long searchLogId);
}
