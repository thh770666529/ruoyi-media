package com.ruoyi.web.controller.media;

import java.util.List;

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
import com.ruoyi.media.domain.MediaTag;
import com.ruoyi.media.service.IMediaTagService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 电影标签Controller
 *
 * @author thh
 * @date 2021-09-11
 */
@RestController
@RequestMapping("/media/tag")
public class MediaTagController extends BaseController {
    @Autowired
    private IMediaTagService mediaTagService;

    /**
     * 查询电影标签列表
     */
    @PreAuthorize("@ss.hasPermi('media:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(MediaTag mediaTag) {
        startPage();
        List<MediaTag> list = mediaTagService.selectMediaTagList(mediaTag);
        return getDataTable(list);
    }

    /**
     * 导出电影标签列表
     */
    @PreAuthorize("@ss.hasPermi('media:tag:export')")
    @Log(title = "电影标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MediaTag mediaTag) {
        List<MediaTag> list = mediaTagService.selectMediaTagList(mediaTag);
        ExcelUtil<MediaTag> util = new ExcelUtil<MediaTag>(MediaTag.class);
        util.exportExcel(response, list, "电影标签数据");
    }

    /**
     * 获取电影标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('media:tag:query')")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId) {
        return AjaxResult.success(mediaTagService.selectMediaTagByTagId(tagId));
    }

    /**
     * 新增电影标签
     */
    @PreAuthorize("@ss.hasPermi('media:tag:add')")
    @Log(title = "电影标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MediaTag mediaTag) {
        return toAjax(mediaTagService.insertMediaTag(mediaTag));
    }

    /**
     * 修改电影标签
     */
    @PreAuthorize("@ss.hasPermi('media:tag:edit')")
    @Log(title = "电影标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MediaTag mediaTag) {
        return toAjax(mediaTagService.updateMediaTag(mediaTag));
    }

    /**
     * 删除电影标签
     */
    @PreAuthorize("@ss.hasPermi('media:tag:remove')")
    @Log(title = "电影标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds) {
        return toAjax(mediaTagService.deleteMediaTagByTagIds(tagIds));
    }
}
