package com.ruoyi.website.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.media.domain.PlayLogs;
import com.ruoyi.system.util.TokenUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.SearchLogMapper;
import com.ruoyi.website.domain.SearchLog;
import com.ruoyi.website.service.ISearchLogService;

/**
 * 搜索日志Service业务层处理
 *
 * @author thh
 * @date 2021-12-11
 */
@Service
public class SearchLogServiceImpl implements ISearchLogService {
    @Autowired
    private SearchLogMapper searchLogMapper;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 查询搜索日志
     *
     * @param searchLogId 搜索日志主键
     * @return 搜索日志
     */
    @Override
    public SearchLog selectSearchLogBySearchLogId(Long searchLogId) {
        return searchLogMapper.selectSearchLogBySearchLogId(searchLogId);
    }

    /**
     * 查询搜索日志列表
     *
     * @param searchLog 搜索日志
     * @return 搜索日志
     */
    @Override
    public List<SearchLog> selectSearchLogList(SearchLog searchLog) {
        return searchLogMapper.selectSearchLogList(searchLog);
    }

    /**
     * 新增搜索日志
     *
     * @param keyword 关键字
     * @return 结果
     */
    @Override
    public int insertSearchLog(String keyword) {
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        SearchLog searchLog = new SearchLog();
        if (loginUser != null) {
            searchLog.setUserId(loginUser.getUserId() + "");
        }
        searchLog.setCreateTime(DateUtils.getNowDate());
        searchLog.setKeyword(keyword);
        setCurrentDeviceInfo(searchLog);
        return searchLogMapper.insertSearchLog(searchLog);
    }

    /**
     * 设置设备信息
     *
     * @param searchLog
     */
    private void setCurrentDeviceInfo(SearchLog searchLog) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        Browser browser = userAgent.getBrowser();
        String browserInfo = browser.getName();
        Version version = userAgent.getBrowserVersion();
        if (version != null) {
            browserInfo += " " + version.getVersion();
        }
        searchLog.setBrowser(browserInfo);
        OperatingSystem os = userAgent.getOperatingSystem();
        searchLog.setOperatingSystem(os.getName());
        searchLog.setIp(ip);
        String addressInfo = AddressUtils.getRealAddressByIP(ip);
        if (!"内网IP".equals(addressInfo)) {
            String[] addressArr = addressInfo.split(" ");
            searchLog.setProvince(addressArr[0]);
            searchLog.setCity(addressArr[1]);
        }
    }


    /**
     * 修改搜索日志
     *
     * @param searchLog 搜索日志
     * @return 结果
     */
    @Override
    public int updateSearchLog(SearchLog searchLog) {
        return searchLogMapper.updateSearchLog(searchLog);
    }

    /**
     * 批量删除搜索日志
     *
     * @param searchLogIds 需要删除的搜索日志主键
     * @return 结果
     */
    @Override
    public int deleteSearchLogBySearchLogIds(Long[] searchLogIds) {
        return searchLogMapper.deleteSearchLogBySearchLogIds(searchLogIds);
    }

    /**
     * 删除搜索日志信息
     *
     * @param searchLogId 搜索日志主键
     * @return 结果
     */
    @Override
    public int deleteSearchLogBySearchLogId(Long searchLogId) {
        return searchLogMapper.deleteSearchLogBySearchLogId(searchLogId);
    }
}
