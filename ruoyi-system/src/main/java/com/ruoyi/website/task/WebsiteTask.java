package com.ruoyi.website.task;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.FfmpegResolutionEnum;
import com.ruoyi.common.enums.VideoStatus;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ffmpeg.MultimediaInfo;
import com.ruoyi.common.utils.ffmpeg.VideoInfo;
import com.ruoyi.common.utils.ffmpeg.VideoSize;
import com.ruoyi.common.utils.ffmpeg.VideoUtils;
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.mapper.VideoMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.website.domain.WebConfig;
import com.ruoyi.website.mapper.AccountMapper;
import com.ruoyi.website.service.IAccountService;
import com.ruoyi.website.service.IWebConfigService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName WebsiteTask
 * @Description 站点定时任务
 * @Author thh
 * @Date 12:05 2021/12/24
 **/
@Component("websiteTask")
public class WebsiteTask {

    private static final Logger log = LoggerFactory.getLogger(WebsiteTask.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private IAccountService accountService;

    /**
     * 初始化账户列表
     */
    public void initAccountList() {
        // 查询出需要初始化账户的用户
        List<SysUser> userList = sysUserMapper.selectNotAccountList();
        // 循环添加账户表数据
        for (SysUser sysUser : userList) {
            accountService.insertUserAccount(sysUser);
        }
    }
}
