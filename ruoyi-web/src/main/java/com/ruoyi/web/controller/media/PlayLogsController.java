package com.ruoyi.web.controller.media;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.system.util.TokenUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.media.domain.PlayLogs;
import com.ruoyi.media.service.IPlayLogsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 播放记录Controller
 *
 * @author thh
 * @date 2021-12-05
 */
@RestController
@RequestMapping("/media/playLogs")
public class PlayLogsController extends BaseController {
    @Autowired
    private IPlayLogsService playLogsService;

    @Autowired
    private TokenUtil tokenUtil;

    //userid	用户 id，不可为空
    //videoid	视频 id，不可为空
    //customid	自定义 id，String类型，最大长度为100，不可为空
    //date	查询日期，日期不能大于今日，格式为yyyy-MM-dd，不可为空
    //num_per_page	返回信息时，每页包含的日志条数 注：允许范围为1~5000
    //page	当前页码

    /**
     * 查询播放记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PlayLogs playLogs) {
        startPage();
        List<PlayLogs> list = playLogsService.selectPlayLogsList(playLogs);
        return getDataTable(list);
    }


    /**
     * 获取播放记录详细信息
     */
    @PostMapping("/getPlayLogs")
    public AjaxResult getPlayLogs(@RequestBody PlayLogs playLogs) {
        return success(playLogsService.selectPlayLogsByVideoId(playLogs.getVideoId()));
    }

    /**
     * 新增或者更新播放记录
     */
    @PostMapping("/insertOrUpdatePalyLogs")
    public AjaxResult insertOrUpdatePalyLogs(@RequestBody PlayLogs playLogs) {
        return toAjax(playLogsService.insertOrUpdatePalyLogs(playLogs));
    }
}
