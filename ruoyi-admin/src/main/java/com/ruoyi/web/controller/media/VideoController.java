package com.ruoyi.web.controller.media;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.media.vo.UploadVideoVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.service.IVideoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.validation.constraints.NotNull;

/**
 * 电影视频Controller
 *
 * @author thh
 * @date 2021-05-16
 */
@RestController
@RequestMapping("/media/video")
public class VideoController extends BaseController
{
    @Autowired
    private IVideoService videoService;

    /**
     * 查询电影视频列表
     */
    @PreAuthorize("@ss.hasPermi('media:video:list')")
    @GetMapping("/list")
    public TableDataInfo list(Video video)
    {
        startPage();
        List<Video> list = videoService.selectVideoList(video);
        return getDataTable(list);
    }

    /**
     * 导出电影视频列表
     */
    @PreAuthorize("@ss.hasPermi('media:video:export')")
    @Log(title = "电影视频", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Video video)
    {
        List<Video> list = videoService.selectVideoList(video);
        ExcelUtil<Video> util = new ExcelUtil<Video>(Video.class);
        return util.exportExcel(list, "电影视频数据");
    }

    /**
     * 获取电影视频详细信息
     */
    @PreAuthorize("@ss.hasPermi('media:video:query')")
    @GetMapping(value = "/{videoId}")
    public AjaxResult getInfo(@PathVariable("videoId") Long videoId)
    {
        return AjaxResult.success(videoService.getById(videoId));
    }

    /**
     * 新增电影视频
     */
    @PreAuthorize("@ss.hasPermi('media:video:add')")
    @Log(title = "电影视频", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Video video)
    {
        return toAjax(videoService.saveOrUpdate(video));
    }

    /**
     * 修改电影视频
     */
    @PreAuthorize("@ss.hasPermi('media:video:edit')")
    @Log(title = "电影视频", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Video video)
    {
        return toAjax(videoService.saveOrUpdate(video));
    }

    /**
     * 删除电影视频
     */
    @PreAuthorize("@ss.hasPermi('media:video:remove')")
    @Log(title = "电影视频", businessType = BusinessType.DELETE)
	@DeleteMapping("/{movieVideoIds}")
    public AjaxResult remove(@PathVariable @NotNull Long[] movieVideoIds)
    {
        List<@NotNull Long> idList = Arrays.asList(movieVideoIds);

        return toAjax(videoService.removeByIds(idList));
    }
}
