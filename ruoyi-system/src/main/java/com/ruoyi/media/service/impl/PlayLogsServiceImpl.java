package com.ruoyi.media.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.system.util.TokenUtil;
import com.ruoyi.website.domain.Comment;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.media.mapper.PlayLogsMapper;
import com.ruoyi.media.domain.PlayLogs;
import com.ruoyi.media.service.IPlayLogsService;

/**
 * 播放记录Service业务层处理
 *
 * @author thh
 * @date 2021-12-05
 */
@Service
public class PlayLogsServiceImpl implements IPlayLogsService {
    @Autowired
    private PlayLogsMapper playLogsMapper;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 查询播放记录列表
     *
     * @param playLogs 播放记录
     * @return 播放记录
     */
    @Override
    public List<PlayLogs> selectPlayLogsList(PlayLogs playLogs) {
        return playLogsMapper.selectPlayLogsList(playLogs);
    }

    /**
     * 新增播放记录
     *
     * @param playLogs 播放记录
     * @return 结果
     */
    @Override
    public int insertOrUpdatePalyLogs(PlayLogs playLogs) {
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        PlayLogs queryPlayLog = new PlayLogs();
        queryPlayLog.setVideoId(playLogs.getVideoId());
        String userId = null;
        if (loginUser != null) {
            userId = loginUser.getUserId() + "";
            queryPlayLog.setUserId(userId);
        } else {
            setCurrentDeviceInfo(queryPlayLog);
        }
        PlayLogs dbPlayLogs = playLogsMapper.selectPlayLogsByCondition(queryPlayLog);
        if (dbPlayLogs != null) {
            dbPlayLogs.setUserId(userId);
            setCurrentDeviceInfo(dbPlayLogs);
            dbPlayLogs.setPlayPosition(playLogs.getPlayPosition());
            dbPlayLogs.setPlayDuration(playLogs.getPlayDuration());
            dbPlayLogs.setUpdateTime(DateUtils.getNowDate());
            return playLogsMapper.updatePlayLogs(dbPlayLogs);
        } else {
            playLogs.setUserId(userId);
            setCurrentDeviceInfo(playLogs);
            playLogs.setCreateTime(DateUtils.getNowDate());
            return playLogsMapper.insertPlayLogs(playLogs);
        }
    }

    /**
     * 保存当前评论时的设备信息
     *
     * @param playLogs
     */
    private void setCurrentDeviceInfo(PlayLogs playLogs) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        Browser browser = userAgent.getBrowser();
        String browserInfo = browser.getName();
        Version version = userAgent.getBrowserVersion();
        if (version != null) {
            browserInfo += " " + version.getVersion();
        }
        playLogs.setBrowser(browserInfo);
        OperatingSystem os = userAgent.getOperatingSystem();
        playLogs.setOperatingSystem(os.getName());
        playLogs.setIp(ip);
        String addressInfo = AddressUtils.getRealAddressByIP(ip);
        if (!"内网IP".equals(addressInfo)) {
            String[] addressArr = addressInfo.split(" ");
            playLogs.setProvince(addressArr[0]);
            playLogs.setCity(addressArr[1]);
        }
    }


    @Override
    public PlayLogs selectPlayLogsByVideoId(String videoId) {
        if (videoId == null) {
            throw new RuntimeException("videoId不得为空");
        }
        PlayLogs playLogs = new PlayLogs();
        playLogs.setVideoId(videoId);
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        String userId = null;
        if (loginUser != null) {
            userId = loginUser.getUserId() + "";
            playLogs.setUserId(userId);
        } else {
            setCurrentDeviceInfo(playLogs);
        }
        return playLogsMapper.selectPlayLogsByCondition(playLogs);
    }
}
